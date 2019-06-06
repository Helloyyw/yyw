package com.xmcc.springboot_demo.config;


import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayConfig {

    @Autowired
    private WeiXinPropertyties weiXinPropertyties;//导入微信配置文件

    //把你要实例化的对象转化成一个Bean，放在IoC容器中
    @Bean
    public BestPayServiceImpl bestPayService() {
        //支付类, 所有方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(weiXinPropertyties.getAppid());//设置微信公众号的appid
        wxPayH5Config.setAppSecret(weiXinPropertyties.getSecret());// 设置微信公众号的app corpSecret
        wxPayH5Config.setMchId(weiXinPropertyties.getMchId());// 设置商户号
        wxPayH5Config.setMchKey(weiXinPropertyties.getMchKey());// 设置商户密钥
        wxPayH5Config.setKeyPath(weiXinPropertyties.getKeyPath());// 设置商户证书路径
        wxPayH5Config.setNotifyUrl(weiXinPropertyties.getNotifyUrl());// 设置支付后异步通知url
        return wxPayH5Config;
    }

}
