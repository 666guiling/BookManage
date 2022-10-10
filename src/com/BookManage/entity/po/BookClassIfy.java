package com.BookManage.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@ClassName : BookClassIfy
@Author : 不会吧
@Date: 2022/9/24 14:37
@Description : 
*/
public class BookClassIfy {
    private Integer id;
    private String name;
    private Integer bookclassNum;

    public BookClassIfy(Integer id, String name, Integer bookclassNum) {
        this.id = id;
        this.name = name;
        this.bookclassNum = bookclassNum;
    }

    public BookClassIfy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBookClassNum() {
        return bookclassNum;
    }

    public void setBookClassNum(Integer bookClassNum) {
        this.bookclassNum = bookClassNum;
    }

    @Override
    public String toString() {
        return "BookClassIfy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookClassNum='" + bookclassNum + '\'' +
                '}';
    }
}
