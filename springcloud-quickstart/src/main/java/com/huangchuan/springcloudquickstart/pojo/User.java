package com.huangchuan.springcloudquickstart.pojo;

/**
 * Description:
 * User: HC
 * Date: 2020-05-03-19:54
 */
public class User {
    private String name;
    private String address;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {

    }

    public User(String name, String address, Integer age) {

        this.name = name;
        this.address = address;
        this.age = age;
    }
}
