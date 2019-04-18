package com.xmcc.springboot_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    private String detailId;
    private String orderId;//订单id
    private String productId;//商品id
    private String productName;//商品名称
    private BigDecimal productPrice;//商品单价.
    private Integer productQuantity;//商品数量.
    private String productIcon;//商品小图.

}
