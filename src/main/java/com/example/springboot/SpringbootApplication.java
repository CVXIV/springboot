package com.example.springboot;

import com.example.springboot.filter.TimeFilter;
import com.example.springboot.listener.ListenerTest;
import com.example.springboot.servlet.ServletTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import java.util.EnumSet;


@SpringBootApplication
public class SpringbootApplication implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

    @Override
    public void onStartup(ServletContext servletContext){
        // 配置 Servlet
        servletContext.addServlet("servletTest",new ServletTest())
                .addMapping("/servletTest");
        // 配置过滤器
        servletContext.addFilter("timeFilter",new TimeFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
        // 配置监听器
        servletContext.addListener(new ListenerTest());
    }
}
