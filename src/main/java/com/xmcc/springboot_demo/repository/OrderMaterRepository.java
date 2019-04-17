package com.xmcc.springboot_demo.repository;


import com.xmcc.springboot_demo.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMaterRepository  extends JpaRepository<OrderMaster,String> {
}
