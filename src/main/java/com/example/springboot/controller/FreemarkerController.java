package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map) {
        map.put("msg", "Hello Freemarker");
        return "hello";
    }
}
