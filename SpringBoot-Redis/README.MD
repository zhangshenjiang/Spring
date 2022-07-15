#Spring整合Redis
***
## 一、系统环境参数
+ IntelliJ Idea 2021.3
+ Maven archetype quickstart
+ Junit 5
+ Jdk 8

## 二、Spring基本配置
配置Maven项目基础环境，运行Hello应用。
### 1.新建Maven项目或模块
采用Maven archetype quickstart模式新建项目或模块
包路径:com.xln.spring

### 2.添加依赖
在项目的pom.xml中添加如下依赖
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <version>2.6.6</version>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
  </dependency>
</dependencies>

```

### 3.配置appliction.yml
在src/main中新建resources目录，并添加一个“application.yml”文件：
```yaml
spring:
  redis:
    host: 192.168.110.153
    password: redis7
    port: 6379
    database: 0
```
### 4.编写控制器
在src/main中新建“java”目录，在其中新建包：com.xln.spring.controller
在控制目录中新建控制类HelloContoller.java，内容如下：
```java
package com.xln.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.RequestWrapper;

@Controller
public class HelloController {

    /**
     * 控制器中的方法hello
     *      设置请求路径，通过http://ip/xxx/hello访问
     * @return
     */
    @RequestWrapper("hello")
    public ModelAndView hello(){

        ModelAndView modelAndView = new ModelAndView();
        /**
         * 设置模型中的数据对象，在视图中可通过属性名称访问
         */
        modelAndView.addObject("msg","Hello Spring MVC!");

        /**
         * 设置视图名称，应用实际调用sayhello.jsp视图
         */
        modelAndView.setViewName("sayhello");
        return  modelAndView;
    }
}
```
### 5.编写web.xml文件
由于Idea默认创建的web.xml使用的web-app 2.3，版本过低！为了支持web应用新特性需要使用web-app 4.0版，
所以在需用如下内容替换掉原始web.xml文件的内容：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <display-name>Archetype Created Web Application</display-name>

    <!--    &lt;!&ndash; characterEncodingFilter字符编码过滤器 &ndash;&gt;-->
    <!--    <filter>-->
    <!--        <filter-name>characterEncodingFilter</filter-name>-->
    <!--        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>-->
    <!--        <init-param>-->
    <!--            &lt;!&ndash;要使用的字符集，一般我们使用UTF-8(保险起见UTF-8最好)&ndash;&gt;-->
    <!--            <param-name>encoding</param-name>-->
    <!--            <param-value>UTF-8</param-value>-->
    <!--        </init-param>-->
    <!--        <init-param>-->
    <!--            &lt;!&ndash;是否强制设置request的编码为encoding，默认false，不建议更改&ndash;&gt;-->
    <!--            <param-name>forceRequestEncoding</param-name>-->
    <!--            <param-value>false</param-value>-->
    <!--        </init-param>-->
    <!--        <init-param>-->
    <!--            &lt;!&ndash;是否强制设置response的编码为encoding，建议设置为true，下面有关于这个参数的解释&ndash;&gt;-->
    <!--            <param-name>forceResponseEncoding</param-name>-->
    <!--            <param-value>true</param-value>-->
    <!--        </init-param>-->
    <!--    </filter>-->
    <!--    <filter-mapping>-->
    <!--        <filter-name>characterEncodingFilter</filter-name>-->
    <!--        &lt;!&ndash;这里不能留空或者直接写 ' / ' ，否者不起作用&ndash;&gt;-->
    <!--        <url-pattern>/*</url-pattern>-->
    <!--    </filter-mapping>-->


    <!--配置DispatcherServlet-->
    <servlet>
        <servlet-name>springMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <!--配置spring-mvc.xml作为mvc的配置文件-->
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <!--表示启动容器时初始化servlet -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springMVC</servlet-name>
        <!--这里拦截请求 "/"表示所有请求，“*.do"表示所有.do请求-->
        <url-pattern>/</url-pattern>
        <!--        <url-pattern>*.do</url-pattern>-->
    </servlet-mapping>

</web-app>

```
### 6.编写视图页面
在src/webapp/WEB-INF中新建“jsp”目录，在其中新建sayhello.jsp文件，内容如下：
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SayHelloPage</title>
</head>
<body>
<%--通过EL表达式获取数据--%>
<h2>${msg}</h2>
</body>
</html>
```
### 7.配置Tomcat服务：
打开“Run->Edit Configures...”：  
+ 添加一个Tomcat Server的Local服务;
+ 选本地Tomcat9.x路径（不要用Tomcat 10.x）;
+ 在Deployment页面中添加一个Artifact,Application Context设置为"/SpringMVC";
+ 在Server页面中的on frame deactivation中选择“update classes and resources";

### 8.运行SpringMVC应用：
启动Tomcat服务，在浏览器中访问：

http://localhost:8080/SpringMVC/hello

系统显示如下：
![](.README.MD_images/db616125.png)

### 9.解决Tomcat控制台乱码问题：
在Idea中"help->Edit Custom VM Options..."中添加如下参数并重启Idea：
-Dfile.encoding=UTF-8

### 10.Tomcat 10.x 支持问题：
由于Sping 5.x 使用javax名空间，暂不支持Tomcat 10.x中的Jakarta名空间，
Sping开发团队规划Spring将在6.x版对Tomcat 10.x进行支持，当前最高支持Tomcat 9.x！


## 二、SpringMVC基本应用

### 1.应用@RequestMapping设置访问路径
有三种方式：
+ 在Controller类上直接设置； 
  所设置的路径可以控制所有类中的方法
+ 在Controller类的方法上设置；  
  所设置的路径只影响当前方法 
+ 在类和方法上同时设置。
  方法访问路径为：类路局/方法路径

可通过method属性设置请求类别：GET，POST，PUT和DELETE，
实际调用过程中如果请求类型不对，系统会报错！
```java
package com.xln.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rm")
public class RequestMapController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public ModelAndView hello(){

        ModelAndView modelAndView = new ModelAndView();
        /**
         * 设置模型中的数据对象，在视图中可通过属性名称访问
         */
        modelAndView.addObject("msg","Hello Spring MVC!");

        /**
         * 设置视图名称
         */
        modelAndView.setViewName("sayhello");
        return  modelAndView;
    }
}

```
访问路径为：http://localhost:8080/SpringMVC/rm/hello

### 2.应用@RequestParam设置请求参数
调用格式要求：
+ 注解要放置在方法的参数名前面；
+ 通过name属性设置别名；
+ 通过required属性设置参数是否必填；
+ 通过default属性设置默认值；
+ 如果是基本类型，必须设置default属性，否则参数为空会报错
+ 如果是数组类型，调用时使用同样的参数名称重复赋值，比如：
+ 如果是对象类型，参数名称必须同对象属性名保持一致。

#### 基本类型及封装类型参数调用：
```java
package com.xln.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rm")
public class RequestMapController {

  /**
   * 如果参数类型为基本类型，需要设置默认值；包装类型则不需要。
   * name/value属性设置参数的别名，也就是request使用的名称，解决请求参数名同实际参数名不一致问题
   * required属性设置参数是否为必须项，default属性设置参数的默认值
   * @param name ：对象类型参数
   * @param age : 基本类型参数
   * @param hobby : 数组类型参数
   */
  @RequestMapping(value = "baseparam",method = RequestMethod.GET)
  public ModelAndView baseparam(@RequestParam(name = "u_name",required=true) String name,
                                @RequestParam(defaultValue="20") int age,
                                String[]  hobby){
    ModelAndView modelAndView = new ModelAndView();
    String u_hobby=" Hobby:";
    for (String val:hobby) {
      u_hobby+=val;
      u_hobby+=",";
    }
    modelAndView.addObject("msg",name+":" + age + u_hobby);
    modelAndView.setViewName("sayhello");
    return  modelAndView;
  }
}

```
访问地址：
http://localhost:8080/SpringMVC/rm/baseparam?u_name=Tom&age=19&hobby=game&hobby=tennis&hobby=swim

输出结果：Tom:19 Hobby:game,tennis,swim,

#### 用户定义对象类型调用：

定义用户对象类：
```java
package com.xln.spring.po;

public class User {
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```
控制类：

```java
package com.xln.spring.controller;

import User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rm")
public class RequestMapController {
  /**
   * 参数类型为用户定义对象类型
   * @param user ：用户定义对象类型参数
   */
  @RequestMapping(value = "objectparam", method = RequestMethod.GET)
  public ModelAndView objecparam(User user) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("msg", user.toString());
    modelAndView.setViewName("sayhello");
    return modelAndView;
  }
}

```
访问地址：
http://localhost:8080/SpringMVC/rm/objectparam?name=Tom&age=19

输出结果：User{name='Tom', age=19}

#### List类型调用：
list类型数据必须封装在用户自定义对象类型才能使用
定义带list对象属性的对象类：
```java
package com.xln.spring.po;

import java.util.ArrayList;
import java.util.List;

public class UserWithPhones {
    String name;
    Integer age;
    List<String> phones =new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "UserWithPhones{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + phones +
                '}';
    }
}

```
在webapp下新建userinfo.jsp:   
list对象类型必须采用attribute[x-1]方式命名
```html

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserInfoPage</title>
</head>
<body>
<form action="./rm/userinfo" method="post">
    Name:<input name="name" /><br />
    Age:<input name="age" /><br />
    Phone1:<input name="phones[0]" /><br />
    Phone2:<input name="phones[1]" /><br />
    Phone3:<input name="phones[2]" /><br />
    <button type="submit">Submit</button>
</form>
</body>
</html>

```

访问地址：
http://localhost:8080/SpringMVC/userinfo.jsp
![](.README.MD_images/5de9bd15.png)
输出结果：UserWithPhones{name='TOM', age=28, phones=[136, 137, 138]}


