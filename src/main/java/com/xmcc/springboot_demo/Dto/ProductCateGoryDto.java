package com.xmcc.springboot_demo.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.springboot_demo.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCateGoryDto implements Serializable {

    @JsonProperty("name")
    private  String  categoryName;
    @JsonProperty("type")
    private Integer  categoryType;
    @JsonProperty("foods")
   public List<ProductInfoDto> productInfoDtoList;

    //实体类转化dto的方法
    public static ProductCateGoryDto build(ProductCategory productCategory) {
        ProductCateGoryDto productCateGoryDto = new ProductCateGoryDto();
        BeanUtils.copyProperties(productCategory, productCateGoryDto);
        return productCateGoryDto;
    }

}
