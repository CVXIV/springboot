package com.example.springboot.service;

import com.example.springboot.pojo.User;

public interface userService {
    int insert(User user);

    int deleteById(Integer id);

    int update(User user);

    User getById(Integer id);
}
