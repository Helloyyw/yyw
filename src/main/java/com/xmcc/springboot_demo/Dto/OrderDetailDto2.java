package com.xmcc.springboot_demo.Dto;

import com.xmcc.springboot_demo.entity.OrderDetail;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("订单项详情实体")
public class OrderDetailDto2 {

     private String   detailId;
    private String orderId;
    private String    productId;
    private String    productName;
    private BigDecimal    productPrice;
    private Integer    productQuantity;
    private String    productIcon;
    private String     productImage;
    //实体类转化dto的方法
    public static OrderDetailDto2 build(OrderDetail orderDetail) {
        OrderDetailDto2 orderDetailDto2 = new OrderDetailDto2();
        BeanUtils.copyProperties(orderDetail, orderDetailDto2);
        return orderDetailDto2;
    }


}
