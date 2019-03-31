package com.dayuan.service;

import com.dayuan.entity.User;
import com.dayuan.exception.BizException;
import com.dayuan.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    //登录
    public User login(String userName,String pwd){
        //  2018/7/22 校验参数
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(pwd)){
            throw new BizException("请输入账号或密码");
        }

        User user = userMapper.seUserByUserName(userName);
        if (user==null|| !user.getPassword().equals(pwd)){
            throw new BizException("账号或密码错误");
        }
        return user;

    }


    //注册
    public void loginUp(String userName, String pwd,String conPwd){
        //  2018/7/23 校验参数
        if (StringUtils.isBlank(userName)||StringUtils.isBlank(pwd) ||StringUtils.isBlank(conPwd)){
            throw new BizException("请填写账户名或密码");
        }

        if (!(pwd.equals(conPwd))){
            throw new BizException("两次输入密码不一致");
        }

        User user = userMapper.seUserByUserName(userName);
        if (user!=null){
            throw new BizException("该用户名已存在");
        }

        //创建账户
        User user1 = new User();
        user1.setUsername(userName);
        user1.setPassword(pwd);
        user1.setCreateTime(new Date());

        int i = userMapper.insertSelective(user1);
        if(i!=1){
            throw new BizException("注册失败");
        }


    }

    //获取用户名
    public String getUserName(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        return user.getUsername();
    }
}
