package com.example.springboot.controller;

import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import com.example.springboot.websocket.WebSocketPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @RequestMapping("/checkUser")
    public String checkUser(User user,RedirectAttributes attr) {
        String username=user.getUsername();
        User result;
        //如果直接通过URL调用或者正常登录，但密码或用户名不正确，直接返回首页
        if(username==null||(result=userService.getByName(username))==null||!result.getPassword().equals(user.getPassword())){
            return "redirect:/login/loginPage";
        }
        attr.addAttribute("user",result.getId());
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
            currentUsers.remove(user);
            session.removeAttribute("user");
        }
        return "loginPage";
    }

    @RequestMapping("/success")
    public String success(HttpServletRequest request,Integer user){
        if(user==null){
            return "redirect:/login/loginPage";
        }
        synchronized (currentUsers){
            HttpSession newsession=request.getSession();
            //如果该用户已经登录
            if(currentUsers.containsKey(user)){
                HttpSession oldsession=currentUsers.get(user);
                webSocketPush.sendErrorMessageToOne("不能同时登陆两个账号",user);
                currentUsers.remove(user);
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
            newsession.setAttribute("user",user);
            currentUsers.put(user,newsession);
        }
        return "success";
    }

}
