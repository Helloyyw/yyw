package com.xmcc.springboot_demo.common;

import lombok.Getter;
import sun.print.ServiceDialog;

@Getter
public enum OrderEnum {
    NEW(0,"新建订单"),
    FINSH(1,"已完成订单"),
    CANCEL(2,"已取消"),
     ORDER_NOT_EXITS (3,"订单不存在"),
AMOUNT_CHECK_ERROR (4,"订单金额不一致");
    private int code;
    private String msg;

    OrderEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
