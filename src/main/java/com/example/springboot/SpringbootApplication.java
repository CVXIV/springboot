package com.example.springboot;

import com.example.springboot.filter.LoginFilter;
import com.example.springboot.listener.ListenerTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import java.util.EnumSet;


@SpringBootApplication
@MapperScan("com.example.springboot.dao")
public class SpringbootApplication implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

    @Override
    public void onStartup(ServletContext servletContext){
        // 配置 Servlet
        /*servletContext.addServlet("servletTest",new ServletTest())
                .addMapping("/servletTest");*/
        // 配置过滤器
        servletContext.addFilter("LoginFilter",new LoginFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/fun/*");
        // 配置监听器
        servletContext.addListener(new ListenerTest());
    }
}
