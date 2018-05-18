package com.example.springboot.controller;

import com.example.springboot.Mail.AuthMail;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {

    private final AuthMail authMail;

    @Autowired
    public RestfulController(AuthMail authMail, UserService userService) {
        this.authMail = authMail;
    }

    @GetMapping("/restful")
    public String getAuthCode(String email){
        String result="成功";
        try {
            authMail.sendMail(email);
        } catch (Exception e) {
            result="无效的邮箱地址";
        }
        return result;
    }
    @GetMapping("/checkAuthCode")
    public String checkAuthCode(String code,String email){
        return authMail.checkCode(code,email)?"验证成功":"验证码错误";
    }
}
