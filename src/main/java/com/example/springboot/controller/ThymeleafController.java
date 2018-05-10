package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map) {
        map.put("msg", "Hello Thymeleaf");
        return "hello";
    }

    @GetMapping("/chat")
    public String test(Map<String,Object> map) {
        return "websocketchat";
    }
}
