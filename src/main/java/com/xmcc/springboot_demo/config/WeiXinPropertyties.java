package com.xmcc.springboot_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeiXinPropertyties {
    private  String appid;
    private   String secret;

    private  String mchId;//商户号(在微信支付平台查看)
    private  String mchKey;//密匙(在微信支付平台自行设置,要求32位.建议使用随机密码)
    private  String keyPath;//证书路径：退款密匙(应该这么叫吧.需要去微信支付平台下载.指定密匙的绝对地
    private  String notifyUrl;//支付成功之后的回调地址

}
