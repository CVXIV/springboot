package com.example.springboot;

import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	@Autowired
	private UserService userService;
	@Test
	public void contextLoads() {
	    User user=userService.getById(3);
	    System.out.println(user.getBirthday());
	}

}
