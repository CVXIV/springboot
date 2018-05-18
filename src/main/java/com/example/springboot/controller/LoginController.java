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

    @GetMapping("/check")
    public String check(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "success";
        }
        return "redirect:/login/loginPage";
    }

    @RequestMapping("/checkUser")
    public String checkUser(HttpServletRequest request, User user) {
        String username=user.getUsername();
        //如果直接通过URL调用
        if(username==null){
            return "redirect:/login/check";
        }
        User result=userService.getByName(username);
        //如果正常登录，但密码或用户名不正确，直接返回首页
        if(result==null||!result.getPassword().equals(user.getPassword())){
            return "redirect:/login/loginPage";
        }
        synchronized (currentUsers){
            HttpSession newsession=request.getSession();
            //如果该用户已经登录
            if(currentUsers.containsKey(result.getId())){
                HttpSession oldsession=currentUsers.get(result.getId());
                webSocketPush.sendErrorMessageToOne("不能同时登陆两个账号",result.getId());
                currentUsers.remove(result.getId());
                oldsession.removeAttribute("user");
            }else{
                //该用户没有登录，但用的是同一个浏览器
                for(HttpSession session:currentUsers.values()){
                    if(session.getId().equals(newsession.getId())){
                        Integer olduser=(Integer) session.getAttribute("user");
                        webSocketPush.sendErrorMessageToOne("之前窗口已经失效",olduser);
                        currentUsers.remove(olduser);
                        session.removeAttribute("user");
                        break;
                    }
                }
            }
            newsession.setAttribute("user",result.getId());
            currentUsers.put(result.getId(),newsession);
        }
        return "success";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer user=(Integer) session.getAttribute("user");
        if(user!=null){
            webSocketPush.sendErrorMessageToOne("你已下线",user);
            currentUsers.remove(user);
            session.removeAttribute("user");
        }
        return "redirect:/login/loginPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

}
