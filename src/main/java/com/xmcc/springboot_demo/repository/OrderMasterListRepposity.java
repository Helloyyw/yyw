package com.xmcc.springboot_demo.repository;
import com.xmcc.springboot_demo.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
public interface OrderMasterListRepposity extends CrudRepository<OrderMaster, String> {

    Page<OrderMaster> findAllByBuyerOpenid(String openid, Pageable pageable);
}
