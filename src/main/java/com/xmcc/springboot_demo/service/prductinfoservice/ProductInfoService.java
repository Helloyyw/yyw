package com.xmcc.springboot_demo.service.prductinfoservice;

import com.xmcc.springboot_demo.common.ResultResponse;

import com.xmcc.springboot_demo.entity.ProductInfo;
import org.springframework.stereotype.Service;

@Service
public interface ProductInfoService{
    ResultResponse queryList();
    ResultResponse<ProductInfo> findById(String productId);
    void updateProduct(ProductInfo productInfo);
}
