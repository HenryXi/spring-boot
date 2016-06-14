package com.henryxi.post.json;

import java.io.Serializable;

public class UserVo implements Serializable {

    private static final long serialVersionUID = 3267101817151303146L;

    private String name;
    private int age;

    public UserVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
