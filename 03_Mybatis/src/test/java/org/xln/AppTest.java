package org.xln;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.xln.dao.UserDao;
import org.xln.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void dbCon() {

        try {
            InputStream inputStream = null;
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
//            System.out.println(sqlSession.toString());

            UserDao userDao = sqlSession.getMapper(UserDao.class);

            User user = new User();
//            user.setId(100);
            user.setAge(28);
            user.setName("李云四");
            user.setBirthday(new Date());

            int count = userDao.save(user);
            System.out.println("系统生成的ID为:" + user.getId());
            sqlSession.commit();
            System.out.println("影响数据行数:" + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
