package com.dayuan.controller;

import com.dayuan.captcha.GifCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CaptchaController {

//    验证码
    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpSession session, HttpServletResponse response){
        GifCaptcha captcha = new GifCaptcha(146, 33, 5);
        try {
            captcha.out(response.getOutputStream());
            session.setAttribute("captcha",captcha.text());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
