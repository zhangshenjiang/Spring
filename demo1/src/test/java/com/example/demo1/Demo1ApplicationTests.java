package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Demo1ApplicationTests {
//    @Autowired CacheService cacheService;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    StringRedisTemplate redis;

    @Test
    void test() {

//        cacheService.add("test1", 12351);
//        cacheService.add("cn2", "中国2");
        redisUtil.setValue("act","测试元素类型为 组织 的字段，无法在列表中显示出来！");
        System.out.println(redisUtil.getValue("cn"));
    }

    @Test
    void redist(){
        redis.opsForValue().set("yeah","测试数据");
        System.out.println(redis.opsForValue().get("act"));
    }
}
