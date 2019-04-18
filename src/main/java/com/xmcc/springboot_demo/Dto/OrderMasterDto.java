package com.xmcc.springboot_demo.Dto;

import com.xmcc.springboot_demo.entity.OrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单参数实体类")
public class OrderMasterDto implements Serializable {

    @NotBlank(message = "买家姓名不能为空")
    @ApiModelProperty(value = "买家姓名",dataType = "String")
    private  String name;
    @NotBlank(message = "买家电话不能为空")
    @Length(min = 11,max = 11,message = "电话不能为空")
    @ApiModelProperty(value = "买家电话",dataType = "String")
    private  String phone;
    @NotBlank(message = "购买地址不能为空")
    @ApiModelProperty(value = "购买地址",dataType = "String")
    private  String address;
    @NotBlank(message = "必须输入用户的微信openid")
    @ApiModelProperty(value = "授权码",dataType = "String")
    private  String openid;
    @NotEmpty(message = "订单项不能为空")
    @Valid //表示需要嵌套验证
    @ApiModelProperty(value = "订单项集合",dataType = "List")
    private List<OrderDetailDto> items;

}
