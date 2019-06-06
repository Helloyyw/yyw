package com.xmcc.springboot_demo.service.orderservice;

import com.google.common.collect.Maps;
import com.xmcc.springboot_demo.Dto.OrderDetailDto;
import com.xmcc.springboot_demo.Dto.OrderDetailDto2;
import com.xmcc.springboot_demo.Dto.OrderMasterDto;
import com.xmcc.springboot_demo.Dto.OrderMasterListDto;
import com.xmcc.springboot_demo.Param.OrderDetailParam;
import com.xmcc.springboot_demo.Param.Param;
import com.xmcc.springboot_demo.common.*;
import com.xmcc.springboot_demo.entity.OrderDetail;
import com.xmcc.springboot_demo.entity.OrderMaster;
import com.xmcc.springboot_demo.entity.ProductInfo;
import com.xmcc.springboot_demo.exception.MyException;
import com.xmcc.springboot_demo.repository.OrderDetailRepository;

import com.xmcc.springboot_demo.repository.OrderMaterRepository;
import com.xmcc.springboot_demo.service.PayService;
import com.xmcc.springboot_demo.service.prductinfoservice.ProductInfoService;
import com.xmcc.springboot_demo.util.BigDecimalUtil;
import com.xmcc.springboot_demo.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class OrderMaterServiceImpl implements OrderMaterService {

      @Autowired
      private OrderMaterRepository orderMaterRepository;
      @Autowired
      private ProductInfoService productInfoService;
      @Autowired
      private  OrderDetailService orderDetailService;
    @Autowired
    private  OrderDetailRepository orderDetailRepository;
    @Autowired
    private PayService payService;
    @Override
    @Transactional//增删改触发事务
    public ResultResponse insertOrder(OrderMasterDto orderMasterDto) {
    //定义一个订单项集合来存贮所有的订单项
        List<OrderDetail> orderDetailList = new ArrayList<>();
        //创建订单总金额为0  涉及到钱的都用 高精度计算
        BigDecimal totalPrice = new BigDecimal("0");

        //构建订单项集合信息然后批量插入：从前端传过来的数据中获取productId
        List<OrderDetailDto> items = orderMasterDto.getItems();

        //遍历这个dto集合取出productId去查询产品的详细信息然后构建多个订单项对象
        for(OrderDetailDto item:items){
            ResultResponse<ProductInfo> resultResponse  =  productInfoService.findById(item.getProductId());
            //获得查询的商品
            ProductInfo productInfo = resultResponse.getData();
            //说明该商品 库存不足 订单生成失败 直接抛出异常 事务才会回滚
            if(productInfo.getProductStock()<item.getProductQuantity()){
                throw new MyException(ProductEnums.PRODUCT_NOT_ENOUGH.getMsg());
            }
            //将前台传入的订单项DTO与数据库查询到的 商品数据组装成OrderDetail 放入集合中
            OrderDetail orderDetail = OrderDetail.builder().detailId(IDUtils.createIdbyUUID()).productIcon(productInfo.getProductIcon())
                    .productId(item.getProductId()).productName(productInfo.getProductName()).productPrice(productInfo.getProductPrice())
                    .productQuantity(item.getProductQuantity()).build();
            //把单个的订单项对象加入到集合
            orderDetailList.add(orderDetail);
            //减少商品库存
            productInfo.setProductStock(productInfo.getProductStock()-item.getProductQuantity());
            productInfoService.updateProduct(productInfo);
            //计算价格
            totalPrice =BigDecimalUtil.add(totalPrice,BigDecimalUtil.multi(productInfo.getProductPrice(),item.getProductQuantity()));
        }
         //生成订单id
        String orderId = IDUtils.createIdbyUUID();
        //构建订单对象信息
        OrderMaster orderMaster= OrderMaster.builder().buyerAddress(orderMasterDto.getAddress()).buyerName(orderMasterDto.getName())
                .buyerOpenid(orderMasterDto.getOpenid()).buyerPhone(orderMasterDto.getPhone()).orderAmount(totalPrice)
                .orderStatus(OrderEnum.NEW.getCode()).payStatus(PayEnum.WAIT.getCode()).orderId(orderId).build();

        //把生成的订单id设置进订单项集合中去
        List<OrderDetail> orderDetails =  orderDetailList.stream().map(orderDetail ->{
                orderDetail.setOrderId(orderId);
         return orderDetail;
        }).collect(Collectors.toList());

        //批量插入订单项
        orderDetailService.batchInsert(orderDetails);
        //插入订单
        orderMaterRepository.save(orderMaster);
        HashMap<String, String> map = Maps.newHashMap();
        //按照前台要求的数据结构传入
        map.put("orderId",orderId);
        return ResultResponse.success(map);
    }

    /**
     * 订单查询
     * @param param
     * @return
     */
    @Override
    public ResultResponse findAllOrderList(Param param) {
//          获取出page和size
        Integer page = param.getPage();
        Integer size = param.getSize();
        String openid = param.getOpenid();
//        构建分页查询对象
        Pageable pageable = new PageRequest(page,size);
        //根据openid去查询订单获取oderid然后去查询订单项列表
       List<OrderMasterListDto> orderMasterListDtos = new ArrayList<>();
//利用jpa的分页查询功能得到page对象
        Page<OrderMaster> orderMasterlist =  orderMaterRepository.findByBuyerOpenid(openid, pageable);
        //然后得到list订单集合遍历集合封装为dto集合
        List<OrderMaster> orderMasters = orderMasterlist.getContent();

        for(OrderMaster orderMaster:orderMasters){
//            //查询订单项集合
//            List<OrderDetail> orderDetailList =orderDetailRepository.findAllByorderId(orderMaster.getOrderId());
          //  OrderMasterListDto.setOrderDetailList(orderDetailList);
            //判断订单为0 的就加入到dto集合
             if(orderMaster.getOrderStatus() == 0){
                 orderMasterListDtos.add(OrderMasterListDto.build(orderMaster));
             }
        }
        return ResultResponse.success(orderMasterListDtos);
    }

    /**
     * 订单详情
     *
     * @return
     */
    @Override
    public ResultResponse findOrderDetail(OrderDetailParam orderDetailParam) {
        //根据订单id和用户授权码查询到订单对象
        ResultResponse<OrderMaster> byId = orderMaterRepository.findByBuyerOpenidAndOrderId(orderDetailParam.getOpenid(),orderDetailParam.getOrderId());
        OrderMaster orderMaster = byId.getData();
        //判断是否为空
        if(byId == null){
            return ResultResponse.fail(orderDetailParam.getOpenid()+":"+ ResultEnums.NOT_EXITS.getMsg());
        }
        OrderMasterListDto orderMasterListDto = OrderMasterListDto.build(orderMaster);
        //根据orderId查询对应的集合
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByorderId(orderDetailParam.getOrderId());
        List<OrderDetailDto2> orderDetailDto2sList = new ArrayList<>();
        if(orderDetailList.size()>0){
            for(OrderDetail orderDetail:orderDetailList){
                orderDetailDto2sList.add(OrderDetailDto2.build(orderDetail));
            }
        }
        orderMasterListDto.setOrderDetailList(orderDetailDto2sList);



        return ResultResponse.success(orderMasterListDto);
    }
/**取消订单
    @Override、
    */
@Override
@Transactional
    public ResultResponse cancelOrderByid(OrderDetailParam orderDetailParam) {
        //查询到订单
        ResultResponse<OrderMaster> byId = orderMaterRepository.findByBuyerOpenidAndOrderId(orderDetailParam.getOpenid(), orderDetailParam.getOrderId());
    OrderMaster orderMaster = byId.getData();
        //判断是否为空
        if (byId == null) {
            return ResultResponse.fail(orderDetailParam.getOpenid() + ":" + ResultEnums.NOT_EXITS.getMsg());
        }
  if(byId.getCode() == PayEnum.FAIL.getCode()){
    return byId;
  }
  if(orderMaster.getOrderStatus() == PayEnum.FINSH.getCode()){
//退款
      payService.refound(orderMaster);

  }
       orderMaster.setPayStatus(OrderEnum.CANCEL.getCode());
        orderMaterRepository.save(orderMaster);

        return ResultResponse.success();

    }
}
