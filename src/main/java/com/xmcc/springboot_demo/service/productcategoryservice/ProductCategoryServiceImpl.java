package com.xmcc.springboot_demo.service.productcategoryservice;

import com.xmcc.springboot_demo.Dto.ProductCateGoryDto;
import com.xmcc.springboot_demo.common.ResultResponse;
import com.xmcc.springboot_demo.entity.ProductCategory;
import com.xmcc.springboot_demo.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    ProductCategoryRepository productCategoryRepository;

    @Override
    public ResultResponse<List<ProductCateGoryDto>> findAll() {
        //首先我要返回商品分类的dto集合，我要先获取他的子集合ProductInfoDto，
        //注意：查询出来的实体类需要转换为dto
       List<ProductCategory> productCateGoryList = productCategoryRepository.findAll();
       //然后用流便利转化为dto
        return  ResultResponse.success(productCateGoryList.stream().map(productCategory ->

                ProductCateGoryDto.build(productCategory)).collect(Collectors.toList()));
    }
}
