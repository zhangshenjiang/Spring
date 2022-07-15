package com.xln.spring.dao;

import com.xln.spring.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {

    int addUser(User user);
    int deleteUser(Integer id);
    int updateUser(User user);

    // 查询行总数
    int getCount();

    List<User> queryAll();
    List<User>  queryByID(Integer id);

    // 模糊查询和字符拼接
    List<User>  queryByName(String name);

    // 多参数查询时必须使用@Param注解
    List<User>  queryByNameAndAge(@Param("name") String name, @Param("age") Integer age);

    // 使用ResultMap封装对象属性
    List<User> queryAllUseResultMap();

    // 分页查询，@Param("alias")中的alias可在Mybatis中使用
    List<User> queryPage(@Param("startIndex") Integer start,@Param("pageRows") Integer rows);

}
