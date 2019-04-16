package com.xmcc.springboot_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_info")
public class ProductInfo implements Serializable {
    //Id
    @Id
    private String productId;
    //产品名
    private String productName;
    //产品价格
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //产品描述
    private String productDescription;
    //产品图片
    private String productIcon;
    //产品状态
    private  Integer productStatus;
    //产品类目Id
    private  Integer categoryType;
    private Date createTime;

    private Date updateTime;
}
