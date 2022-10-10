package com.BookManage.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@ClassName : User
@Author : 不会吧
@Date: 2022/9/24 14:38
@Description : 
*/

public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String imgUrl;

    public User() {
    }

    public User(Integer id, String username, String password, String phone, String sex, String imgUrl) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.imgUrl = imgUrl;
    }

    public User(Integer id, String username, String phone, String sex) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.sex = sex;
    }

    public User(String username, String password, String phone, String sex, String imgUrl) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.imgUrl = imgUrl;
    }
public User(Integer id,String username, String phone, String sex, String imgUrl) {
        this.id=id;
        this.username = username;
        this.phone = phone;
        this.sex = sex;
        this.imgUrl = imgUrl;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
