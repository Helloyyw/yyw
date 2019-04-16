package com.xmcc.springboot_demo.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.springboot_demo.entity.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto implements Serializable {

    @JsonProperty("id")//该注解表示把此属性的名字标识为和接口文档需要的属性名字一样
    private Integer productId;
    //产品名字
    @JsonProperty("name")
    private String productName;
    //单价
    @JsonProperty("price")
    private String productPrice;
    //产品描述
    @JsonProperty("description")
    private String productDescription;
    //小图
    @JsonProperty("icon")
    private String productIcon;
    //在我们创建好dto的同时还有创建一个方法来把数据库实体类转化为我们的扩展dtolei

    public static ProductInfoDto build(ProductInfo productInfo) {
        ProductInfoDto productInfoDto = new ProductInfoDto();
        BeanUtils.copyProperties(productInfo, productInfoDto);
        return productInfoDto;
    }
}
