package com.xln.sping.spingbootmybatisplus.mapper;

import com.xln.sping.spingbootmybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void getListUserSqlTest() {
        List<User> userList = userMapper.getListUserSql();
        userList.forEach(System.out::println);
    }

    @Test
    void getListUserXmlTest() {
        List<User> userList = userMapper.getListUserXml();
        userList.forEach(System.out::println);
    }

    @Test
    void getMapUserXmlTest() {
        Map<Long, User> userMap;
        userMap = userMapper.getMapUserXml();
        for (Long m:userMap.keySet()) {
            System.out.println(m+"=>"+userMap.get(m));
        }
    }

    @Test
    void getOneUserByIDXml() {
        User user;
        user = userMapper.getOneUserByIDXml(2L);
        System.out.println(user);
    }

    @Test
    void getOneUserByIDSql() {
        User user;
        user = userMapper.getOneUserByIDSql(3L);
        System.out.println(user);
    }

}