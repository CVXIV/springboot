package com.example.springboot.controller;

import com.example.springboot.Mail.AuthMail;
import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestfulController {

    private final AuthMail authMail;
    private final UserService userService;

    @Autowired
    public RestfulController(AuthMail authMail, UserService userService) {
        this.authMail = authMail;
        this.userService=userService;
    }

    @PostMapping("/restful")
    public Map<String,Object> save(User user) {
        userService.insert(user);
        Map<String,Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "保存成功");
        return map;
    }

    @GetMapping("/restful/{id}")
    public Map<String,Object> get(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "获取成功");
        map.put("data", user);
        return map;
    }

    @PutMapping("/restful")
    public Map<String,Object> update(User user) {
        userService.update(user);
        Map<String,Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "修改成功");
        return map;
    }

    @DeleteMapping("/restful/{id}")
    public Map<String,Object> delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "删除成功");
        return map;
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
