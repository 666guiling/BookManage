package com.BookManage.dao;

import com.BookManage.entity.po.User;

import java.util.List;

/*
@ClassName : BaseDao
@Author : 不会吧
@Date: 2022/9/24 14:54
@Description : 
*/
public interface BaseDao<T> {
    //根据用户名查询用户  返回用户名查询个数
    long checkUsername(String username);

    //根据账号密码查询用户 返回用户id
    int queryIDByBean(T obj);

    //根据id查询用户信息
    List<T> queryUser(int id);

    //根据id修改用户信息
    long updateUser(User user);

    //根据id，密码 查询用户 返回查询个数
    long queryUserByPwd(int id ,String password);

    //根据id修改密码 返回影响行数
    long updatePassword(int id,String password);

}
