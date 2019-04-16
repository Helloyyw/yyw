package com.xmcc.springboot_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity//声明该类表示实体类
@DynamicUpdate//设置为true表示update对象时生成动态update语句,如果这个字段的值是null就不会被加入到update语句中
@Data//相当于set、get、toString方法
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")//表名
public class ProductCategory implements Serializable {
    /**
     * 类目Id
     */
    @Id//主键
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示mysql的自增：： SEQUENCE:oracle
    private Integer categoryId;
    /**
     * 类目name
     */
    private  String categoryName;
    /**
     * 类目name
     */
    private  Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
