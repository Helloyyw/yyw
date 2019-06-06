package com.xmcc.springboot_demo.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfig {
    @Autowired
    private WeiXinPropertyties weixinProperties;
//
    @Bean
    public WxMpService wxMpService() {
     //创建一个WxMpService对象
        WxMpService wxMpService = new WxMpServiceImpl();
    //创建本地存储
        wxMpService.setWxMpConfigStorage(getWxMapConfigStorage());
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage getWxMapConfigStorage() {
        //new 一个存贮对象
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        //设置appid
        wxMpInMemoryConfigStorage.setAppId(weixinProperties.getAppid());
        //设置screte
        wxMpInMemoryConfigStorage.setSecret(weixinProperties.getSecret());

        return wxMpInMemoryConfigStorage;
    }
}
