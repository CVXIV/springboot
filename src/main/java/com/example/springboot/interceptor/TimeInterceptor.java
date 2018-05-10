package com.example.springboot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        System.out.println("========preHandle=========");
        //System.out.println(((HandlerMethod)handler).getClass().getName());
        //System.out.println(((HandlerMethod)handler).getMethod().getName());
        System.out.println(new Date());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){

        System.out.println("========postHandle=========");
        System.out.println(new Date());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception){

        System.out.println("========afterCompletion=========");
        System.out.println(new Date());
    }

}
