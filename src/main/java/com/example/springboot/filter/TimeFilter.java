package com.example.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("=======初始化过滤器=========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        long start = System.currentTimeMillis();
        System.out.println("进入filter");
        filterChain.doFilter(request, response);
        System.out.println("离开filter 耗时：" + (System.currentTimeMillis() - start));

    }

    @Override
    public void destroy() {
        System.out.println("=======销毁过滤器=========");
    }

}
