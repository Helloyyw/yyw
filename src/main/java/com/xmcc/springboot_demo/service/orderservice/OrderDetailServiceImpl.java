package com.xmcc.springboot_demo.service.orderservice;

import com.xmcc.springboot_demo.Dao.BatchImpl;
import com.xmcc.springboot_demo.entity.OrderDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderDetailServiceImpl extends BatchImpl<OrderDetail> implements OrderDetailService{
    @Override
    @Transactional//开启事务
    public void batchInsert(List<OrderDetail> list) {
        super.batchInsert(list);
    }
}
