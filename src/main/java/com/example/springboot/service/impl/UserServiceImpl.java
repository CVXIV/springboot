package com.example.springboot.service.impl;

import com.example.springboot.dao.UserMapper;
import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@CacheConfig(cacheNames = "user")
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @CachePut(key = "#user.id")
    @Override
    public int insert(User user) {
        System.out.println("保存 id=" + user.getId() + " 的数据");
        return userMapper.insert(user);
    }

    @CacheEvict(key = "#id")
    @Override
    public int deleteById(Integer id) {
        System.out.println("删除 id=" + id + " 的数据");
        return userMapper.deleteById(id);
    }

    @CachePut(key = "#user.id")
    @Override
    public int update(User user) {
        System.out.println("修改 id=" + user.getId() + " 的数据");
        return userMapper.update(user);
    }

    @Cacheable(key = "#id")
    @Override
    public User getById(Integer id) {
        System.out.println("获取 id=" + id + " 的数据");
        return userMapper.getById(id);
    }

    @Cacheable(key = "#username")
    @Override
    public User getByName(String username) {
        System.out.println("获取 username=" + username + " 的数据");
        return userMapper.getByName(username);
    }
}
