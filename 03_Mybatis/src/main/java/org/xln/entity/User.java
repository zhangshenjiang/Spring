package org.xln.entity;

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
