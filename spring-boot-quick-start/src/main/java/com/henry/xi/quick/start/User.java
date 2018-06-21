package com.henry.xi.quick.start;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private String name;

    private String age;

    public User() {
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}' + "\n";
    }
}
