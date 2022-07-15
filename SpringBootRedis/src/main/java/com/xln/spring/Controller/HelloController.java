package com.xln.spring.Controller;

import com.xln.spring.RedisString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    StringRedisTemplate redis;


    @GetMapping("/hello")
    public String hello() {
        RedisString redisString = new RedisString();
        redisString.set();
        return "Hello Spring Boot";
    }

    @GetMapping("/redis")
    public String redis() {
        return redis.opsForValue().get("act");
    }

}