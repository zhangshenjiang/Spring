package com.xln.spring.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    static SqlSessionFactory sqlSessionFactory;
    static {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static SqlSession getSenssion(){
        return sqlSessionFactory.openSession();
    }

    public static void closeSession(SqlSession sqlSession){
        sqlSession.close();
    }
}
