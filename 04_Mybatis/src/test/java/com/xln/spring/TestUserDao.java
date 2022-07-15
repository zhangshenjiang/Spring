package com.xln.spring;

import com.xln.spring.dao.UserDAO;
import com.xln.spring.entity.User;
import com.xln.spring.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserDao {
    @Test
    public void TestInsertUser(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        User user=new User();
        user.setId(108);
        user.setName("王小燕");
        user.setAge(36);
        user.setBirthday(new Date());
        int iRtn=userDAO.addUser(user);
        sqlSession.commit();
        MybatisUtil.closeSession(sqlSession);
        assertEquals(iRtn,1);
    }

    @Test
    public void TestDeleteUser(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        int iRtn=userDAO.deleteUser(108);
        sqlSession.commit();
        MybatisUtil.closeSession(sqlSession);
        assertEquals(iRtn,1);
    }
    @Test
    public void TestUpdateUser(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        User user=new User();
        user.setId(108);
//        user.setName("张小燕");
        user.setAge(37);
//        user.setBirthday(new Date());
        int iRtn=userDAO.updateUser(user);
        sqlSession.commit();
        MybatisUtil.closeSession(sqlSession);
        assertEquals(iRtn,1);
    }


    @Test
    public void TestGetCount(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        int iCount=userDAO.getCount();
        System.out.println("总用户数："+iCount);
        MybatisUtil.closeSession(sqlSession);
    }


    @Test
    public void TestQuerryAll(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        List<User> userList=userDAO.queryAll();
        userList.forEach(user -> System.out.println(user.toString()));
        MybatisUtil.closeSession(sqlSession);
    }

    @Test
    public void TestQuerryAllUseResultMap(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        List<User> userList=userDAO.queryAllUseResultMap();
        userList.forEach(user -> System.out.println(user.toString()));
        MybatisUtil.closeSession(sqlSession);
    }

    @Test
    public void TestQuerryById(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        List<User> userList=userDAO.queryByID(101);
        userList.forEach(user -> System.out.println(user.toString()));
        MybatisUtil.closeSession(sqlSession);
    }

    @Test
    public void TestQuerryByName(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        List<User> userList=userDAO.queryByName("张");
        userList.forEach(user -> System.out.println(user.toString()));
        MybatisUtil.closeSession(sqlSession);
    }

    @Test
    public void TestQuerryByNameAndAge(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        List<User> userList=userDAO.queryByNameAndAge("云",26);
        userList.forEach(user -> System.out.println(user.toString()));
        MybatisUtil.closeSession(sqlSession);
    }

    @Test
    public void TestQuerryPage(){
        SqlSession sqlSession= MybatisUtil.getSenssion();
        UserDAO userDAO=sqlSession.getMapper(UserDAO.class);
        List<User> userList=userDAO.queryPage(2,3);
        userList.forEach(user -> System.out.println(user.toString()));
        MybatisUtil.closeSession(sqlSession);
    }
}
