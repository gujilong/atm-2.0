package com.dayuan.service;

import com.dayuan.entity.User;
import com.dayuan.entity.WxBingInfo;
import com.dayuan.exception.BizException;
import com.dayuan.mapper.UserMapper;
import com.dayuan.mapper.WxBingInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxService {
    @Autowired
    WxBingInfoMapper wxBingInfoMapper;
    @Autowired
    UserMapper userMapper;

    public User saveWx(WxBingInfo info){
        User user =null;
        WxBingInfo wxBingInfo = wxBingInfoMapper.seByOpenid(info.getOpenid());
        if(wxBingInfo==null){
            wxBingInfoMapper.insert(info);
        }else {
            if(wxBingInfo.getUserId()!=null){
               user = userMapper.selectByPrimaryKey(wxBingInfo.getUserId());
            }
            wxBingInfo.setUnionid(info.getUnionid());
            wxBingInfo.setSex(info.getSex());
            wxBingInfo.setProvince(info.getProvince());
            wxBingInfo.setOpenid(info.getOpenid());
            wxBingInfo.setNickname(info.getNickname());
            wxBingInfo.setHeadimgurl(info.getHeadimgurl());
            wxBingInfo.setCountry(info.getCountry());
            wxBingInfo.setCity(info.getCity());
            int i = wxBingInfoMapper.updateByPrimaryKeySelective(wxBingInfo);
            if (i!=1){
                throw new BizException("微信登陆失败");
            }
        }

        return  user;
    }

    public Integer wxBind(String userName , String pwd,String openId){
        //  2018/7/22 校验参数
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(pwd)){
            throw new BizException("请输入账号或密码");
        }
        User user = userMapper.seUserByUserName(userName);
        if (user==null|| !user.getPassword().equals(pwd)){
            throw new BizException("账号或密码错误");
        }
        WxBingInfo wxBingInfo = wxBingInfoMapper.seByOpenid(openId);
        if(wxBingInfo ==null){
           throw new BizException("非法请求") ;
        }
        wxBingInfo.setUserId(user.getId());
        int i = wxBingInfoMapper.updateByPrimaryKey(wxBingInfo);
        if (i!=1){
            throw new BizException("绑定失败");
        }
        return user.getId();
    }

}
