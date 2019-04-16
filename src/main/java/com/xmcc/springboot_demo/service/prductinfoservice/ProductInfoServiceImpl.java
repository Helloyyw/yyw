package com.xmcc.springboot_demo.service.prductinfoservice;


import com.xmcc.springboot_demo.Dto.ProductCateGoryDto;
import com.xmcc.springboot_demo.Dto.ProductInfoDto;
import com.xmcc.springboot_demo.common.ResultEnums;
import com.xmcc.springboot_demo.common.ResultResponse;
import com.xmcc.springboot_demo.entity.ProductInfo;
import com.xmcc.springboot_demo.repository.ProductInfoRepository;
import com.xmcc.springboot_demo.service.productcategoryservice.ProductCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    ProductInfoRepository productInfoRepository;
    @Resource
    ProductCategoryService productCategoryService;

    @Override
    public ResultResponse queryList(){
        //这里面我们是要求得到List<ProductInfo>集合但是我们要更具ProductCateGory里面的类目id和自己的状态来查询的
        //先查询出ProductCateGoryDto集合
        ResultResponse<List<ProductCateGoryDto>> resultResponseList = productCategoryService.findAll();
        List<ProductCateGoryDto> productCateGoryDtos = resultResponseList.getData();
//        判断分类列表是不是为空
        if (CollectionUtils.isEmpty(productCateGoryDtos)) {

            return resultResponseList;//如果分类列表为空 直接返回了
        }

        //获取产品类目type
        List<Integer> categoryType = productCateGoryDtos.stream().map(productCateGoryDto ->
                productCateGoryDto.getCategoryType()).collect(Collectors.toList());
        //根据产品类目id查询对应的产品信息
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatusAndCategoryTypeIn(ResultEnums.PRODUCT_UP.getCode(),categoryType);

        //根据产品类目集合来查询对应类目的产品信息并设置进ProductCateGoryDto对象中去
        List<ProductCateGoryDto> finalProductCateGoryDto = productCateGoryDtos.parallelStream().map(productCateGoryDto ->{
                productCateGoryDto.setProductInfoDtoList(productInfoList.stream().filter(productInfo ->
                    productInfo.getCategoryType() == productCateGoryDto.getCategoryType()
                ).map(productInfo ->ProductInfoDto.build(productInfo)).collect(Collectors.toList()));
                return productCateGoryDto;
        }).collect(Collectors.toList());



        return ResultResponse.success(finalProductCateGoryDto);
    }
}
