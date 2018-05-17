package com.example.springboot.service.impl;

import com.example.springboot.dao.UserMapper;
import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByName(String username) {
        return userMapper.getByName(username);
    }
}
