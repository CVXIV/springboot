package com.example.springboot.controller;

import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import com.example.springboot.websocket.WebSocketPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    @PostMapping("/checkUser")
    public String checkUser(User user,HttpServletRequest request) {
        String username=user.getUsername();
        User result;
        //如果直接通过URL调用或者正常登录，但密码或用户名不正确，直接返回首页
        if(username==null||(result=userService.getByName(username))==null||!result.getPassword().equals(user.getPassword())){
            return "redirect:/login/loginPage";
        }
        Integer userId=result.getId();
        synchronized (currentUsers){
            HttpSession newsession=request.getSession();
            HttpSession oldsession=currentUsers.get(userId);
            //如果该用户已经登录
            if(currentUsers.containsKey(userId)&&!newsession.getId().equals(oldsession.getId())){
                webSocketPush.sendErrorMessageToOne("不能同时登陆两个账号",userId);
                currentUsers.remove(userId);
                oldsession.removeAttribute("user");
            }
            newsession.setAttribute("user",userId);
            currentUsers.put(userId,newsession);
        }
        return "redirect:/login/success";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer user=(Integer) session.getAttribute("user");
        if(user!=null){
            currentUsers.remove(user);
            session.removeAttribute("user");
        }
        return "redirect:/login/loginPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(HttpSession session){
        Integer user=(Integer)session.getAttribute("user");
        if(user!=null){
            return "redirect:/login/success";
        }
        return "loginPage";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

}
