package com.dayuan.handler;

import com.alibaba.fastjson.JSON;
import com.dayuan.dto.AccessTokenDto;
import com.dayuan.entity.WxBingInfo;
import com.dayuan.util.ApiConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxAuthenHandler {
    @Value("${wx.code.url}")
    private String codeUrl;

    @Value("${wx.appid}")
    private String appId;

    @Value("${wx.redirect.uri}")
    private String redirectUrl;

    @Value("${wx.response.type}")
    private String responseType;

    @Value("${wx.scope}")
    private String scope;


    @Value("${wx.access.token.url}")
    private String accessTokenUrl;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grant.type}")
    private String grantType;

    @Value("${wx.user.info.url}")
    private String userInfoUrl;

    /**
     * 生成访问二维码页面的请求地址
     * @return
     */
    public String getCodeUrl(){
        StringBuilder url = new StringBuilder();
        url.append(codeUrl).append("?")
                .append("appId=").append(appId).append("&")
                .append("redirect_uri=").append(redirectUrl).append("&")
                .append("response_type=").append(responseType).append("&")
                .append("scope=").append(scope);
        return url.toString();
    }



    /**
     * 获取微信AccessToken
     */


    public AccessTokenDto getAccessToken(String code){
        StringBuilder url = new StringBuilder();
        url.append(accessTokenUrl).append("?")
                .append("appId=").append(appId).append("&")
                .append("secret=").append(secret).append("&")
                .append("code=").append(code).append("&")
                .append("grant_type=").append(grantType);

        String msg = ApiConnector.get(url.toString(), null);
        System.out.println("msg="+msg);

        AccessTokenDto accessTokenDto = JSON.parseObject(msg,AccessTokenDto.class);
        System.out.println("accessToken=" + accessTokenDto.getAccess_token());

        return accessTokenDto;

    }


    public WxBingInfo getWxBingInfo(AccessTokenDto accessTokenDto){
        StringBuilder url = new StringBuilder();
        url.append(userInfoUrl).append("?")
                .append("access_token=").append(accessTokenDto.getAccess_token()).append("&")
                .append("openid=").append(accessTokenDto.getOpenid());

        String msg = ApiConnector.get(url.toString(), null);
        System.out.println("msg"+msg);
        WxBingInfo wxBingInfo = JSON.parseObject(msg,WxBingInfo.class);

        return wxBingInfo;

    }
}
