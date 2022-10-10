package com.BookManage.entity.bo;

import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.Borrow;
import com.BookManage.entity.po.User;

import java.util.List;

/*
@ClassName : UserBo
@Author : 不会吧
@Date: 2022/9/27 15:19
@Description : 
*/
public class UserBo {
    private User user;
    private List<Book> borrowList;

    public UserBo() {
    }

    public UserBo(User user) {
        this.user = user;
    }

    public UserBo(User user, List<Book> borrowList) {
        this.user = user;
        this.borrowList = borrowList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<Book> borrowList) {
        this.borrowList = borrowList;
    }

    @Override
    public String toString() {
        return "UserBo{" +
                "user=" + user +
                ", borrowList=" + borrowList +
                '}';
    }
}
