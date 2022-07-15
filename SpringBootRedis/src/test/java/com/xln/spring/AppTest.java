package com.xln.spring;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest 
{

    @Autowired
    StringRedisTemplate redis;

    @Test
    void redist(){
        redis.opsForValue().set("yeah","测试数据");
        System.out.println(redis.opsForValue().get("act"));
    }
}
