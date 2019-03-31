package com.dayuan.controller;

import com.dayuan.exception.BizException;

import javax.servlet.http.HttpSession;

public class UserId {
    public static Integer getUserId(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            throw  new BizException("请先登录");
        }
        return userId;
    }
}
