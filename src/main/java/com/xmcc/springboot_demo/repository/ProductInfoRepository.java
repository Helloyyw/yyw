package com.xmcc.springboot_demo.repository;

import com.xmcc.springboot_demo.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    //根据类目编号 查询正常上架的商品
    List<ProductInfo>   findByProductStatusAndCategoryTypeIn(Integer status,List<Integer> categoryList);

}
