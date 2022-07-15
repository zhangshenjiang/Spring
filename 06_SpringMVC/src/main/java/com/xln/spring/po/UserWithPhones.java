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
