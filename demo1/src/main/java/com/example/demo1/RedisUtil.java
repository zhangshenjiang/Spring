package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setValue(String key,String val){
        redisTemplate.opsForValue().set(key,val);
    }
    public String getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
