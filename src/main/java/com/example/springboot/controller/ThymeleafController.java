package com.example.springboot.controller;

import com.example.springboot.pojo.User;
import com.example.springboot.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @Autowired
    private userService userService;
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map) {
        User user=userService.getById(1);
        map.put("msg", user);
        return "hello";
    }

    @GetMapping("/chat")
    public String test() {
        return "websocketchat";
    }
}
