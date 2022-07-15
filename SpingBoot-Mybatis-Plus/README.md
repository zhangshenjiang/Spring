# Using Mybatis-plus in SpringBoot

## 一、系统环境参数
+ IntelliJ Idea 2021.3
+ Spring Initializr Module
+ Mybatis 3.5.9
+ Mysql 8 database
+ Mysql-connector-java 8.0.28
+ Junit 5
+ Jdk 8

## 二、SpringBoot基本配置
配置SpringBoot项目基础环境，运行SpringBoot应用。
### 1. 新建Spring Initializr项目或模块
采用Spring Initializr模式新建项目或模块
包路径:com.xln.spring

### 2. 添加依赖
在项目的pom.xml中添加如下依赖
```xml
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.16</version>
            <scope>provided</scope>
        </dependency>

```
## 三、创建程序基础结构
### 1. 准备基础数据结构
```sql
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);


DELETE FROM user;
INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```
### 2. 创建配置文件application.yml或application.properties： 
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.122.153:3306/dev?useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: mysql
    
mybatis-plus:
  configuration:
    # 开启SQL日志输出
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```
```properties
# 应用名称
spring.application.name=SpingBoot-Mybatis-Plus
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# 数据源名称
spring.datasource.name=mysql8
# 数据库连接地址
spring.datasource.url=jdbc:mysql://192.168.122.153:3306/dev?useSSL=false&allowPublicKeyRetrieval=true
# 数据库用户名&密码：
spring.datasource.username=root
spring.datasource.password=mysql
# 开启SQL日志输出
mybatis-plus.configuration.log-impl= org.apache.ibatis.logging.stdout.StdOutImpl
```
### 3. 创建实体类User和对应的Mapper接口类：
```java
package com.xln.sping.spingbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@TableName
public class User {
    @TableId             // 等同于 @TableId(type = IdType.ASSIGN_ID)，默认采用雪花算法实现主键自增长，无需数据库设置。
    private Long id;     // ID类型在对象中必须使用Long类型，在数据库表中必须使用BigInt类型，为了兼容雪花算法。
    @TableField          // 驼峰匹配规则：如果表字段名user_name，则对象属性可用userName，且可省略@TableField注解
    private String name;
    private Integer age;
    private String email;
}

```
```java
package com.xln.sping.spingbootmybatisplus.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xln.sping.spingbootmybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

// Mybatis3.4.0开始加入了@Mapper注解，从此就可不再写mapper映射文件（XML文件）
// @Mapper注解用于扫描并装载mapper接口类，Mybatis不推荐使用@Repository注解
@Mapper 
public interface UserMapper extends BaseMapper<User> {

}

```
PS：@Data注解包括了@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.


### 4. 在启动类中添加 @MapperScan 注解，扫描 Mapper 文件夹。
——如果仅用于测试用例，可忽略此步骤。
```java
package com.xln.sping.spingbootmybatisplus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xln.sping.spingbootmybatisplus.mapper")
@SpringBootApplication
public class SpingBootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpingBootMybatisPlusApplication.class, args);
    }

}

```

## 五、Mybatis-plus基本操作——基于BaseMapper实现
```java
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
```

## 六、Mybatis-plus自定义SQL操作——基于BaseMapper实现
自定义SQL有两种实现方式:
1. 直接使用SQL注解：

   采用这种方式，无需定mapper.xml。
2. 在mapper.xml文件中定义sql语句。

   采用这种方式需在resource目录中定义mapper目录，并在此目录中定义一个与Mapper接口类同名的xml配置文件。

Mybatis-plus推荐使用第一种方式，代码结构更加简单。

### 添加两种不同自定义SQL操作后的UserMapper文件
```java
package com.xln.sping.spingbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xln.sping.spingbootmybatisplus.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;


// Mybatis3.4.0开始加入了@Mapper注解，从此就可不再写mapper映射文件（XML）
// @Mapper注解用于扫描并装载mapper接口类，Mybatis不推荐使用@Repository注解
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user ORDER BY age")
    List<User> getListUserSql();

    List<User> getListUserXml();

    // Map对象必须指定key字段
    @MapKey("id")
    Map<Long,User> getMapUserXml();

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getOneUserByIDSql(Long id);

    User getOneUserByIDXml(Long id);
}

```
### mapper/UserMapper.xml：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.xln.sping.spingbootmybatisplus.mapper.UserMapper">
    <select id="getListUserXml"  resultType="com.xln.sping.spingbootmybatisplus.entity.User">
        select * from user order by id desc
    </select>


    <select id="getMapUserXml"  resultType="com.xln.sping.spingbootmybatisplus.entity.User">
        select * from user order by id
    </select>

    <select id="getOneUserByIDXml"  resultType="com.xln.sping.spingbootmybatisplus.entity.User">
        select id,name,email from user where id=#{id}
    </select>
</mapper>

```



### 接口测试类
```java
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
```
## 七、Mybatis-plus基本操作——基于IService实现
Mybatis-plus在IService接口及其实现类ServiceImpl中，为我们提供有丰富的数据库操作接口，如果我使用IService接口，数据库操作将会变得非常简便！
### 构建IService实现类UserServiceImpl
1. 新建service.UserService接口，继承IService接口，使用User作为泛型类型。
```java
package com.xln.sping.spingbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xln.sping.spingbootmybatisplus.entity.User;

public interface UserService extends IService<User> {
}

```
2. 新建impl.UserServiceImpl类，继承ServiceImpl类，使用UserMapper和User作为泛型类型，并实现UserService接口。
3. 使用@Service注解将UserServiceImp类标注为service。
```java
package com.xln.sping.spingbootmybatisplus.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xln.sping.spingbootmybatisplus.entity.User;
import com.xln.sping.spingbootmybatisplus.mapper.UserMapper;
import com.xln.sping.spingbootmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

```
### 使用UserServiceImol进行数据库操作
```java
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
```

## 八、Mybatis-plus注解
### @TableName
@TableName("t_user") 指定当前对象对应的数据库表名称为t_user。

### @TableId
@TableId("uid") 指定当前对象属性为主键字段，且其数据库表中对应的字段名称为uid。

@TableId(value="uid",type=IdType.Auto) 主键采用数据库自增方式，确保在数据库表中设置了uid字段为自增长。

@TableId(value="uid",type=IdType.ASSIGN_ID) 主键采用雪花算法自动赋值，主键类型为number或string。

@TableId(value="uid",type=IdType.ASSIGN_UUID) 主键采用不含中划线的UUID算法自动赋值，主键类型只能为string。

### @TableField
@TableField(value="username") 指定非主键字段对应数据库字段名称为username。

@TableField(value="userName") 指定非主键字段对应数据库字段名称为user_name,Mybatis-plus默认驼峰变下划线算法全局配置。

### @TableLogic
@TableLogic用于指定数据库表的逻辑删除控制字段，用来实现数据库表数据的逻辑删除管理，**<font color=red>以便实现用户已删除数据的恢复！</font>**

配置步骤：
1. 在数据库表中增加一个int类型的is_deleted字段，默认值为零。
2. 在对象属性isDeleted上设置@TableLogic注解，该属性用于控制表的逻辑删除功能。
3. 正常使用Mybatis-plus删除表中数据时，所有被删除记录的is_deleted值被设置成1了。
4. 恢复数据时，只需将is_deleted值设置成0即可。


## 九、Mybatis-plus全局设置

### 在application.yml文件中设置
```yaml
mybatis-plus:

  # Mybatis-plus全局配置
  global-config:
    db-config:
      # 设置实体类对应的数据库表的统一前缀，避免在每个实体对象上使用@TableName("t_xxx")
      table-prefix: t_
      
      # 设置数据库表ID统一采用雪花算法自动生成
      id-type: assign_id
    
```