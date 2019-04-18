package com.xmcc.springboot_demo.service.orderservice;

import com.xmcc.springboot_demo.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService  {
    //批量插入
    void batchInsert(List<OrderDetail> orderDetails);


}
