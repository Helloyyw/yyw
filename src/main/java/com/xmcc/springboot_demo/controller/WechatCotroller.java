package com.xmcc.springboot_demo.controller;
import com.xmcc.springboot_demo.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("/wechat")
@Api(description = "微信授权接口")//使用swagger2的注解对类描述
public class WechatCotroller {
    @Autowired
    private WxMpService wxMpService;

    //根据API接口文档 书写路径
    @RequestMapping("/authorize")
    //查看文档需要一个returnUrl参数
    @ApiOperation(value = "授权方法")//使用swagger2的注解对方法接口描述
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //自己编写获得openid的路径 在下面定义方法getUserInfo
        String url = "http://xmccjyqs.natapp1.cc/sell/wechat/getUserInfo";
        //根据sdk文档获得路径  点击方法下载文档 很清晰的解释
        /**
         * 第一个参数是获得授权码code后回调的地址
         * 第二个是策略：获得简单的授权，还是希望获得用户的信息
         * 第三个参数是我们希望携带的参数（state开发者可以填写a-zA-Z0-9的参数值，最多128字节）:
         * 查看API文档需要返回returnUrl 所以我们就携带它
         */
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl,"UTF-8"));
        log.info("redirectUrl获取code超链接地址：{}",redirectUrl);
        return "redirect:"+redirectUrl;
    }
//如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
 //就是跳转到：http://xmcc.natapp1.cc/sell/wechat/getUserInfo？code=用户授权码&state=上面传的redirectUrl
    @RequestMapping("/getUserInfo")
    /**
     * code:是授权码
     * returnUrl：是上面我们自己传递的参数  用户同意后会然后会传回来code和state
     *
     * 我们需要接受这两个参数，然后去获取accesstoken，然后根据令牌和openid去获取用户信息
     */
    public String getUserInfo(@RequestParam("code")String code,
                              @RequestParam("state") String returnUrl) throws UnsupportedEncodingException {
    //定义一个了令牌对象
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;

        try {
            //获取令牌
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            /**
             * 返回信息包含：
             * {
             *     "access_token":"ACCESS_TOKEN",
             *     "expires_in":7200,   //access_token接口调用凭证超时时间，单位（秒）
             *     "refresh_token":"REFRESH_TOKEN",
             *     "openid":"OPENID",
             *     "scope":"SCOPE"
             *  }
             *
             */


        } catch (WxErrorException e) {
            log.error("微信获得access_token异常:{}",e.getError().getErrorMsg());
            throw  new MyException(e.getError().getErrorCode(),e.getError().getErrorMsg());
        }

        try {
            //获取用户信息(wxMpOAuth2AccessToken里面含有accesstoken 和openid)
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);
            log.info("获得用户信息:{}",wxMpUser.getNickname());
/**
 * {返回用户信息包含：
 *     "openid":" OPENID",
 *     " nickname": NICKNAME,
 *     "sex":"1",
 *     "province":"PROVINCE"
 *     "city":"CITY",
 *     "country":"COUNTRY",
 *     "headimgurl":       "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
 *     "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
 *     "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
 * }
 */
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        //获得openid
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return  "redirect:"+ URLDecoder.decode(returnUrl,"UTF-8")+"?openid="+openId;
    }
    //测试是否获得openid
    @RequestMapping("/testOpenid")
    public void testOpenid(@RequestParam("openid")String openid){
        log.info("获得用户的openid为:{}",openid);
    }
}
