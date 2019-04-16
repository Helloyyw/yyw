package com.xmcc.springboot_demo.service.prductinfoservice;

import com.xmcc.springboot_demo.common.ResultResponse;

import org.springframework.stereotype.Service;

@Service
public interface ProductInfoService{
    ResultResponse queryList();
}
