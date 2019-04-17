package com.xmcc.springboot_demo.entity;

import com.xmcc.springboot_demo.common.OrderEnum;
import com.xmcc.springboot_demo.common.PayEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity//声明该类表示实体类
@DynamicUpdate//设置为true表示update对象时生成动态update语句,如果这个字段的值是null就不会被加入到update语句中
@Data//相当于set、get、toString方法
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_master")//表名
public class OrderMaster implements Serializable {
    @Id
    private String orderId;//订单id

    private String buyerName;//购买人
    private String buyerPhone;//购买人电话
    private String buyerAddress;//地址
    private String buyerOpenid;//微信授权码
    private BigDecimal orderAmount;//订单总价
    private Integer orderStatus = OrderEnum.NEW.getCode();//订单状态, 默认为0新下单.
    private Integer payStatus = PayEnum.WAIT.getCode();//支付状态, 默认为0未支付.
    private Date createTime;//创建时间.
    private Date updateTime;//更新时间.

}
