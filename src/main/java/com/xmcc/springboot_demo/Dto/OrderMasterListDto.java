package com.xmcc.springboot_demo.Dto;

import com.xmcc.springboot_demo.entity.OrderMaster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("订单参数实体类")
public class OrderMasterListDto extends OrderMaster implements Serializable {
    @ApiModelProperty(value = "订单项集合",dataType = "List")
    List<OrderDetailDto2> orderDetailList;

    //实体类转化dto的方法
    public static OrderMasterListDto build(OrderMaster orderMaster) {
        OrderMasterListDto orderMasterListDto = new OrderMasterListDto();
        BeanUtils.copyProperties(orderMaster,orderMasterListDto);
        return orderMasterListDto;
    }

}
