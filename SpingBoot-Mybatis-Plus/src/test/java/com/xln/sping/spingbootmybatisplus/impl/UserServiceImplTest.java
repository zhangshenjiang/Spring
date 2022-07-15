package com.xln.sping.spingbootmybatisplus.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xln.sping.spingbootmybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;


    // 测试获取表总行数
    @Test
    public void getUserCount() {
        long count = userService.count();
        System.out.println("总数量为：" + count);
    }

    // 测试新增数据
    @Test
    public void addUser() {
        User user = new User();
        user.setName("Tomsen");
        user.setAge(28);
        user.setEmail(user.getName() + "@qq.com");
        userService.save(user);
    }

    // 测试批量插入数据
    @Test
    public void addMorUsers() {
        List<User> listUser = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName("Tom" + i);
            user.setAge(20 + i);
            user.setEmail(user.getName() + "@qq.com");
            listUser.add(user);
        }
        userService.saveBatch(listUser);
    }

    // 测试修改数据
    @Test
    public void updateUser() {
        User user = userService.getById(3L);
        user.setName("Tomson");
        userService.updateById(user);
    }

    // 测试批量修改数据
    @Test
    public void updateMoreUser() {
        User user = new User();
        user.setName("Tomson");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.ge("id", 1547885483004702730L);
        updateWrapper.le("id", 1547885483004702736L);
        userService.update(user, updateWrapper);
    }

    // 测试删除数据
    @Test
    public void removeUser() {
        userService.removeById(5L);
    }

    // 测试批量删除数据
    @Test
    public void removeMoreUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("id", 1547885483004702724L);
        queryWrapper.lt("id", 1547885483004702727L);
        userService.remove(queryWrapper);
    }

    // 测试获取数据
    @Test
    public void getUser() {
        User user = userService.getById(3L);
        System.out.println(user);
    }

    // 测试获取数据集
    @Test
    public void getMoreUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("name", "Tom");
        // 查询用户名以Tom开头的用户数据
        List<User> userList = userService.list(queryWrapper);
        // 查询所有用户
        // List<User> userList = userService.list();
        userList.forEach(System.out::println);
    }
}