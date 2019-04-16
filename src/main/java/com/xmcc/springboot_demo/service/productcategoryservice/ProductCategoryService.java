package com.xmcc.springboot_demo.service.productcategoryservice;

import com.xmcc.springboot_demo.Dto.ProductCateGoryDto;
import com.xmcc.springboot_demo.common.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductCategoryService {
    ResultResponse<List<ProductCateGoryDto>> findAll();
}
