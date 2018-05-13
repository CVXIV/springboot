package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NormalController {
    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
