package com.BookManage.dao;

import com.BookManage.dao.Impl.UserDaoImpl;
import com.BookManage.entity.bo.BorrowBo;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.Borrow;
import com.BookManage.entity.po.User;
import org.junit.Test;

import java.util.List;

/*
@ClassName : userDao
@Author : 不会吧
@Date: 2022/9/26 15:39
@Description : 
*/
public class TestUserDaoImpl {
    private UserDao userDao = new UserDaoImpl();
    User user;

    @Test
    public void method(){
        List<User> users = userDao.queryUser(2);
        System.out.println(users.get(0).getUsername());
    }
    @Test
    public void queryUser(){
        long l = userDao.queryUserByPwd(2, "123");
        System.out.println(l);
    }
    @Test
    public void method01(){
        long l = userDao.updatePassword(3,"123");
        System.out.println(l);
    }
    @Test
    public void method02(){
        /*List<User> list = userDao.getList();
        for (User user1 : list) {
            System.out.println(user1);
        }*/
    }
    @Test
    public void method03() {
        List<Book> bookList = userDao.getBorrowListByUserId(3);
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    @Test
    public void method04(){
        List<BorrowBo> borrows = userDao.queryBorrow();
        for (BorrowBo borrow : borrows) {
            System.out.println(borrow);
        }
    }
}
