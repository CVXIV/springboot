package com.example.springboot.controller;

import com.example.springboot.Mail.AuthMail;
import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestfulController {

    private final UserService userService;

    private final AuthMail authMail;

    @Autowired
    public RestfulController(AuthMail authMail, UserService userService) {
        this.authMail = authMail;
        this.userService = userService;
    }

    @GetMapping("/restful")
    public User getUser(int id) {
        return userService.getById(id);
    }
    @PostMapping("/restful")
    public int addUser(User user) {
        return userService.insert(user);
    }
    @PutMapping("/restful")
    public int updateUser(User user) {
        return userService.update(user);
    }
    @DeleteMapping("/restful/{id}")
    public int deleteUser(@PathVariable("id") int id) {
        return userService.deleteById(id);
    }
    @GetMapping("/getAuthCode")
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
