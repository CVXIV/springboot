package com.example.springboot.controller;

import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class RestfulController {

    @Resource(name = "userService")
    private UserService userService;
    @GetMapping("/restful")
    public User getUser(int id) {
        return userService.getById(id);
    }
    @PostMapping("/restful")
    public int addUser(User user) {
        return userService.insert(user);
    }
    @PutMapping("/restful")
    public int updateUser(User user) {
        return userService.update(user);
    }
    @DeleteMapping("/restful/{id}")
    public int deleteUser(@PathVariable("id") int id) {
        return userService.deleteById(id);
    }

}
