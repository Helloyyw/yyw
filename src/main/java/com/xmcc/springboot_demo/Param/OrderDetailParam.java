package com.xmcc.springboot_demo.Param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("订单详情实体类")
public class OrderDetailParam {

    @NotBlank(message = "用户授权码不能为空")
    @ApiModelProperty(value="用户授权码",dataType = "String")
    private  String openid;
    @NotBlank(message = "订单id不能为空")
    @ApiModelProperty(value="订单id",dataType = "String")
    private  String orderId;

}
