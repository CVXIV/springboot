package com.example.springboot.dao;

import com.example.springboot.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
     int insert(User user);

     int deleteById(Integer id);

     int update(User user);

     User getById(Integer id);
}