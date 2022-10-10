package com.BookManage.service;

import com.BookManage.entity.po.User;
import com.BookManage.entity.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/*
@ClassName : UserService
@Author : 不会吧
@Date: 2022/9/24 15:05
@Description : 
*/
public interface UserService extends BaseService{
    //用户注册功能
    RespBean addUser(HttpServletRequest request, String username, String password, String phone, String sex,  Part part);

    //获取用户信息
    RespBean queryUserById(int id);

    //伪删除用户
    RespBean delUser(int id);

    //修改用户信息
    RespBean updateUserByID(HttpServletRequest request, int id, String username, String phone, String sex, Part part);

    //借阅图书
    RespBean borrowBook(HttpServletRequest request,int bookID);

    //查看借阅信息
    RespBean queryBorrow(HttpServletRequest request);

    //还书
    RespBean returnBook(int id);

}
