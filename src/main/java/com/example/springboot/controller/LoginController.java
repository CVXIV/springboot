package com.example.springboot.controller;

import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import com.example.springboot.websocket.WebSocketPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final ConcurrentHashMap<Integer,HttpSession> currentUsers=new ConcurrentHashMap<>();
    private final WebSocketPush webSocketPush;
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService, WebSocketPush webSocketPush) {
        this.userService = userService;
        this.webSocketPush = webSocketPush;
    }

    @GetMapping("/initPage")
    public String login(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "success";
        }
        return "login";
    }

    @RequestMapping("/checkUser")
    public String checkUser(HttpServletRequest request, User user) {
        User result=userService.getByName(user.getUsername());
        if(result==null||!result.getPassword().equals(user.getPassword())){
            return "redirect:/login/initPage";
        }
        synchronized (currentUsers){
            if(currentUsers.containsKey(result.getId())){
                HttpSession oldsession=currentUsers.get(result.getId());
                HttpSession newsession=request.getSession();
                //如果被顶替
                if(!oldsession.getId().equals(newsession.getId())){
                    webSocketPush.sendErrorMessageToOne("你被顶了",result.getId());
                }
            }
            request.getSession().setAttribute("user",result.getId());
            currentUsers.put(result.getId(),request.getSession());
        }
        return "success";
    }

}
