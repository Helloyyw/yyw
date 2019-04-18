package com.xmcc.springboot_demo.service.orderservice;


import com.xmcc.springboot_demo.Dto.OrderMasterDto;
import com.xmcc.springboot_demo.Param.OrderDetailParam;
import com.xmcc.springboot_demo.Param.Param;
import com.xmcc.springboot_demo.common.ResultResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderMaterService  {
    //插入订单
    ResultResponse insertOrder(OrderMasterDto orderMasterDto);


    ResultResponse findAllOrderList(Param param);

    ResultResponse findOrderDetail(OrderDetailParam orderDetailParam);

    ResultResponse cancelOrderByid(OrderDetailParam orderDetailParam);
}
