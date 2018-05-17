package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fun")
public class NormalController {
    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/auth")
    public String auth(){
        return "authCode";
    }

}
