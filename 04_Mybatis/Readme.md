#Mybatis应用
***
## 一、系统环境参数
+ IntelliJ Idea 2021.3
+ Maven archetype quikstart 
+ Mybatis 3.5.9
+ Mysql 8 database
+ Mysql-connector-java 8.0.28
+ Junit 5
+ Jdk 8

## 二、Mybatis基本操作（curd）配置
### 1.添加依赖
```xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.9</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.28</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>
</dependencies>
```

### 2.创建Mybatis配置文件
用于创建SqlSessionFactory，获取SqlSession，操作数据库。

配置文件的具体内容，可参考[Mybatis官方入门文档](https://mybatis.org/mybatis-3/getting-started.html)
并从中获取。
在src.main目录中新建Resources资源文件目录，并在其中创建一个名为mybatis-config.xml文件，内容如下：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://192.168.32.172/xln?characterEncoding=utf8"/>
                <property name="username" value="root"/>
                <property name="password" value="mysql8"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="com/xln/spring/dao/UserDAO.xml"/>
    </mappers>
</configuration>
```

### 3.创建数据库表
用下列语句建立一个用户信息表
```sql
CREATE TABLE `user`  (
  `id` int(0) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) ,
  `age` int(0) ,
  `birthday` date 
)
```
### 4.编写实体类
```java
package com.xln.spring.entity;

import java.util.Date;

public class User {
    private Integer id;
    private String Name;
    private Integer age;
    private Date birthday;

    public User() {
    }

    public User(Integer id, String name, Integer age, Date birthday) {
        this.id = id;
        Name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}


```
### 4.编写实体类的数据访问接口
```java
package com.xln.spring.dao;

import com.xln.spring.entity.User;

public interface UserDAO {
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(User user);
    int selectAll();
    int selectByID(Integer id);
    int selectByName(String name);
}


```
### 4.编写DAO接口的Mapper映射文件



#### Mybatis中Mapper配置文件位置
Mybatis中Mapper配置文件一般用DAO接口相同的文件名+mapper命名xml文件，
配置文件存放位置一般有两种方式：
+ mapper配置文件与接口在同一package下  
由于使用基于Maven的构建工具时，默认不会将java下的xml类型文件输出至target，
因此需配置Maven中的build标签，使xxxMapper.xml可以被构建工具扫描到。
有两种常用方式：
  + 直接使用resource标签
  ```xml
      <build>
        <resources>
          <resource>
              <directory>src/main/java</directory>
              <includes>
                  <include>**/*.xml</include>
              </includes>
              <filtering>false</filtering>
          </resource>
        </resources>
      </build>
  ```
  + 使用maven-resources-plugin插件
  ```xml
      <build>
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
          <executions>
            <execution>
              <id>copy-xmls</id>
              <phase>process-sources</phase>
              <goals>
                <goal>copy-resources</goal>
              </goals>
              <configuration>
                <outputDirectory>${basedir}/target/classes</outputDirectory>
                <resources>
                  <resource>
                    <directory>${basedir}/src/main/java</directory>
                    <includes>
                      <include>**/*.xml</include>
                    </includes>
                  </resource>
                </resources>
              </configuration>
            </execution>
          </executions>
        </plugin>
      </build>
  ```
+ mapper配置文件存放于resources下

  + 配置文件可直接放置在resource目录中，在mybatis-config中通过Mapper标签中导入，如下：
  ```xml
     <mapper resource="CountryMapper.xml"/>
  ```

  + 随着mapper的增多，也可在resources下创建与接口所在包一致的文件结构，
  如src/main/resources/com/xln/spring/dao/UserDAOMapper.xml，
  在mybatis-config中通过Mapper标签中导入，如下：
  ```xml
     <mapper resource="com/xln/spring/dao/UserDAOMapper.xml"/>
  ```


## 三、MybatisPlus配置

mybatiscodehlperpro插件

## 四、Mybatis高级配置

## 五、Mybatis集成springboot