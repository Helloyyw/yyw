package com.xmcc.springboot_demo.controller;

import com.lly835.bestpay.model.PayResponse;
import com.xmcc.springboot_demo.common.ResultResponse;
import com.xmcc.springboot_demo.entity.OrderMaster;
import com.xmcc.springboot_demo.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("pay")
@Slf4j
@Api(value = "支付接口",description = "完成订单的支付")
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping("create")
    /**
     * 根据API文档创建接口
     * orderId: 订单ID 这里只能传递一个ID 防止别人传入非法的金额
     * returnUrl: 回调地址
     */
    @ApiOperation(value = "支付接口", httpMethod = "POST", response =ResultResponse.class)//使用swegger接口文档注释
    public ModelAndView create(@RequestParam("orderId")String orderId,
                       @RequestParam("returnUrl")String returnUrl, Map map){
        //根据id查询订单
        OrderMaster orderMaster = payService.findOrderById(orderId);

        //根据订单创建支付
        PayResponse response = payService.create(orderMaster);
        //将参数设置到map中
        map.put("payResponse",response);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("weixin/pay",map);
    }
    @RequestMapping("test")
    @ApiOperation(value = "测试回调接口", httpMethod = "POST", response =ResultResponse.class)//使用swegger接口文档注释
    public void test(){
        log.info("异步回调OK");
    }
}
