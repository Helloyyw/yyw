package com.xmcc.springboot_demo.common;

import lombok.Getter;

@Getter
public enum PayEnum {
    WAIT(0,"等待支付"),
    FINSH(1,"支付完成"),
    FAIL(2,"支付失败"),
    STATUS_ERROR (3,"支付状态异常");
    private int code;
    private String msg;

    PayEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
