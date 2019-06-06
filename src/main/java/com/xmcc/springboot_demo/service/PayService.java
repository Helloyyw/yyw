package com.xmcc.springboot_demo.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.xmcc.springboot_demo.entity.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public interface PayService {
    //根据订单id查询订单
    OrderMaster findOrderById(String orderId);
    //根据订单创建预支付
    PayResponse create(OrderMaster orderMaster);
   //异步通知
    void weixin_notify(String notifyData);
    //微信退款
    RefundResponse refound(OrderMaster orderMaster);
}
