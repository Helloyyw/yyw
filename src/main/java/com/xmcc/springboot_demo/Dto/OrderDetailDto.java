package com.xmcc.springboot_demo.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Data
@ApiModel("订单项实体类")
public class OrderDetailDto implements Serializable {
    @NotBlank(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id",dataType = "String")//swagger 参数的描述信息
    private String productId;

    @Min(value = 1, message = "数量不能少于一件")
    @ApiModelProperty(value = "商品数量",dataType = "Integer")
    private Integer productQuantity;

}
