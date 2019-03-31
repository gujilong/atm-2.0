package com.dayuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/skip")
public class Skip {

    @RequestMapping(value ="/index")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("active","首页");
        return "home";
    }

    @RequestMapping(value ="/openAccount")
    public String openAccount(ModelMap modelMap){
        modelMap.addAttribute("active","开户");
        return "openaccount";
    }

    @RequestMapping(value ="/todraw")
    public String todraw(ModelMap modelMap){
        modelMap.addAttribute("active","取款");
        return "draw";
    }

    @RequestMapping(value ="/deposit")
    public String deposit(ModelMap modelMap){
        modelMap.addAttribute("active","存款");
        return "deposit";
    }

    @RequestMapping(value ="/transfer")
    public String transfer(ModelMap modelMap){
        modelMap.addAttribute("active","转账");
        return "transfer";
    }


    @RequestMapping(value ="/qflow")
    public String qflow(ModelMap modelMap){
        modelMap.addAttribute("active","流水");
        return "flow";
    }


    //跳转登录界面
    @RequestMapping(value ="/loginUp")
    public String loginUp(){
        return "redirect:/index.jsp";
    }


    //跳转注册页面
    @RequestMapping(value ="/tologinUp")
    public String tologinUp(){
        return "redirect:/sign-up.jsp";
    }

}
