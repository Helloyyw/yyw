package com.xmcc.springboot_demo.Param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@ApiModel("订单参数实体类")
public class Param {
    @NotBlank(message = "用户授权码不能为空")
    @ApiModelProperty(value = "授权码",dataType = "String")
    private String openid;
    @NotNull(message = "每页显示的列表不为空")
    @ApiModelProperty(value = "每页显示10条数据",dataType = "Integer")
    private Integer size = 10;
    @NotNull(message = "从0开始")
    @ApiModelProperty(value = "从第一条开始查询",dataType = "Integer")
    private Integer page = 0;

}
