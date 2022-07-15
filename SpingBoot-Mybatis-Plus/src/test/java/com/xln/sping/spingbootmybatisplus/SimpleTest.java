package com.xln.sping.spingbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xln.sping.spingbootmybatisplus.entity.User;
import com.xln.sping.spingbootmybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class SimpleTest {
    @Autowired
    private UserMapper userMapper;

    // 新增用户
    @Test
    public void testInsert(){
        User user=new User();
//        user.setId(6L);
        user.setName("张三");
        user.setAge(18);
        user.setEmail("zhangsan@qq.com");
        int iResult=userMapper.insert(user);
        Assertions.assertEquals(1, iResult);
        System.out.println(userMapper.selectById(user.getId()));
    }

    // 变更用户
    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(6L);
        user.setName("张三华");
        user.setAge(28);
        int iResult=userMapper.updateById(user);
        Assertions.assertEquals(1, iResult);
        System.out.println(userMapper.selectById(user.getId()));
    }

    // 查询所有用户
    @Test
    public void testSelectAll(){
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    // 查询某个用户
    @Test
    public void testSelectOne(){
        User user = userMapper.selectById(2);
        Assertions.assertNotNull(user);
        System.out.println(user);
    }
    // 查询范围
    @Test
    public void testSelectRange(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.gt("id",1);
        queryWrapper.lt("id",4);

        List<User> userList = userMapper.selectList(queryWrapper);
        Assertions.assertEquals(2,userList.size());
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // 删除用户——通过ID
    @Test
    public void testDeleteByID(){
        int iResult=userMapper.deleteById(6l);
        Assertions.assertEquals(0, iResult);
    }
    // 删除用户——通过Map参数
    @Test
    public void testDeleteMap(){
        Map<String,Object> map =new HashMap<>();
        map.put("name","张三");
        map.put("age",18);
        int iResult=userMapper.deleteByMap(map);
        Assertions.assertNotNull(iResult);
    }
}
