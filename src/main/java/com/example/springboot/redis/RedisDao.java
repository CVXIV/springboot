package com.example.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDao {
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisDao(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void set(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key){
        stringRedisTemplate.delete(key);
    }
}
