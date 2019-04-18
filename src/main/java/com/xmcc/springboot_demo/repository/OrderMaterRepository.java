package com.xmcc.springboot_demo.repository;

import com.xmcc.springboot_demo.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderMaterRepository  extends JpaRepository<OrderMaster,String>, CrudRepository<OrderMaster, String>{
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

   OrderMaster findByBuyerOpenidAndOrderId(String openid, String orderId);
}
