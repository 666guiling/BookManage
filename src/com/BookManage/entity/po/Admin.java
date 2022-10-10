package com.BookManage.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@ClassName : Admin
@Author : 不会吧
@Date: 2022/9/24 14:37
@Description : 
*/

public class Admin {
    private Integer id;
    private String adminName;
    private String password;
    private String imgUrl;

    public Admin(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }

    public Admin(String adminName) {
        this.adminName = adminName;
    }

    public Admin(Integer id, String adminName, String password, String imgUrl) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public Admin(Integer id, String adminName) {
        this.id = id;
        this.adminName = adminName;
    }

    public Admin(Integer id, String adminName, String imgUrl) {
        this.id = id;
        this.adminName = adminName;
        this.imgUrl = imgUrl;
    }

    public Admin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
