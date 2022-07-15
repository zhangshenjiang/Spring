package com.xln.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
//        ApplicationContext ctx = new ClassPathXmlApplicationContext();
//        StringRedisTemplate redis=(StringRedisTemplate)ctx.getBean("stringRedisTemplate");
//        System.out.println(redis.opsForValue().get("cn"));
        System.out.println( "Hello World!" );
    }
}
