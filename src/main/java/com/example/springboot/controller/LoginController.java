package com.example.springboot.controller;

import com.example.springboot.websocket.WebSocketPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final ConcurrentHashMap<Integer,HttpSession> currentUsers=new ConcurrentHashMap<>();
    @Autowired
    private WebSocketPush webSocketPush;
    @GetMapping("/checkUser/{user}")
    public String checkUser(HttpServletRequest request, @PathVariable("user") int user) {
        if(currentUsers.containsKey(user)){
            HttpSession oldsession=currentUsers.get(user);
            HttpSession newsession=request.getSession();
            //如果被顶替
            if(!oldsession.getId().equals(newsession.getId())){
                webSocketPush.sendErrorMessageToOne("你被顶了",user);
            }
        }
        request.getSession().setAttribute("user",user);
        currentUsers.put(user,request.getSession());
        return "test";
    }

}
