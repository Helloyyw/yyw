package com.xmcc.springboot_demo.repository;


import com.xmcc.springboot_demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail,String> {
}
