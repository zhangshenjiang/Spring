package com.xln.spring;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    private RedisTemplate redisTemplate;


    @Test
    public void TestResdis()
    {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = connectionFactory.getConnection();


        Set<String> keys=redisTemplate.keys("*");
        keys.forEach(item->{
            System.out.println(item);
        });
    }
}
