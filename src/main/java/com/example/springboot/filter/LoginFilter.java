package com.example.springboot.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("=======初始化登录过滤器=========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+httpServletRequest.getContextPath()+"/";
        HttpSession session=httpServletRequest.getSession();

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("=======销毁登录过滤器=========");
    }

}
