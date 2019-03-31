package com.dayuan.controller;

import com.dayuan.dto.AccessTokenDto;
import com.dayuan.dto.ResponseDTO;
import com.dayuan.entity.User;
import com.dayuan.entity.WxBingInfo;
import com.dayuan.exception.ExceptionDispose;
import com.dayuan.handler.WxAuthenHandler;
import com.dayuan.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("Wx")
public class WxController extends ExceptionDispose {

    @Autowired
    WxService wxService;

    @Autowired
    WxAuthenHandler wxAuthenHandler;
    @RequestMapping("/getCodeUrl")
    @ResponseBody
    public ResponseDTO getCodeUrl(){
        // http://payhub.dayuanit.com/weixin/connect/qrconnect.do?
        // appid=2018072515514057389
        // &redirect_uri=http://192.168.2.232:8088/wx/wxCallBack.do
        // &response_type=code
        // &scope=snsapi_login

        String url = wxAuthenHandler.getCodeUrl();
        return ResponseDTO.success(url);
    }

    @RequestMapping("/wxCallBack")
    public String wxCallBack(String code, HttpSession session){
        System.out.println("code="+code);
        // appid=2018072515514057389
        // &secret=20452912517798515597104200458860
        // &code="+code+" &grant_type=authorization_code
        // String accessTokenUrl = "http://payhub.dayuanit.com/weixin/sns/oauth2/access_token.do?appid=2018072623171024345&secret=88340266289771106788856404582684&code="+code+"&grant_type=authorization_code";

        //  2018/7/26 发送请求获取 access_token
        //  String msg = ApiConnector.get(accessTokenUrl, null);
        //  System.out.println("msg="+msg);
        //  JSONObject jsonObject = JSON.parseObject(msg);

        AccessTokenDto accessTokenDto = wxAuthenHandler.getAccessToken(code);

       // String accessToken = jsonObject.getString("access_token");
       // String openId = jsonObject.getString("openid");


        // 2018/7/26 通过 access_token获取个人信息
        // access_token="+accessToken+"
        // &openid=" + openId
        // String userInfoUrl = "http://payhub.dayuanit.com/weixin/sns/userinfo.do?access_token="+accessToken+"&openid="+openId;
        // msg = ApiConnector.get(userInfoUrl, null);
        // System.out.println("msg"+msg);

        // jsonObject = JSON.parseObject(msg);
        // final WxBingInfo wxBingInfo = new WxBingInfo();
        // wxBingInfo.setCity(jsonObject.getString("city"));
        // wxBingInfo.setCountry(jsonObject.getString("country "));
        // wxBingInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
        // wxBingInfo.setNickname(jsonObject.getString("nickname"));
        // wxBingInfo.setOpenid(jsonObject.getString("openid"));
        // wxBingInfo.setProvince(jsonObject.getString("province"));
        // wxBingInfo.setSex(jsonObject.getString("sex"));
        // wxBingInfo.setUnionid(jsonObject.getString("unionid"));

        WxBingInfo wxBingInfo = wxAuthenHandler.getWxBingInfo(accessTokenDto);
        String headimgurl = wxBingInfo.getHeadimgurl();
        String nickName = wxBingInfo.getNickname();
        session.setAttribute("headimgurl", headimgurl);
        session.setAttribute("nickName", nickName);
        session.setAttribute("openid", accessTokenDto.getOpenid());
        User user = wxService.saveWx(wxBingInfo);
        if(user!=null){
            session.setAttribute("userId", user.getId());
            return "redirect:/skip/index.do";
        }else {
            return "redirect:/Wx/bindPage.do";
        }
    }



    @RequestMapping("/bindPage")
    public String bindPage(){
        return "bindLogin";
    }

    @RequestMapping("/wxBind")
    @ResponseBody
    public ResponseDTO wxBind(String userName,String pwd,HttpSession session){
        String openid = (String)session.getAttribute("openid");
        if(openid==null){
            return ResponseDTO.fail("绑定失败");
        }

        Integer userId = wxService.wxBind(userName, pwd, openid);
        session.setAttribute("userId", userId);

        return ResponseDTO.success();


    }



}
