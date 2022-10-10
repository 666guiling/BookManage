package com.BookManage.dao;

import com.BookManage.entity.po.Admin;

/*
@ClassName : AdminDaoImpl
@Author : 不会吧
@Date: 2022/9/27 11:00
@Description : 
*/
public interface AdminDao extends BaseDao<Admin>{
    //根据id修改个人信息
    long updateAdminById(Admin admin);

    //根据id修改个人信息 不修改头像
    long updateAdminById2(Admin admin);
}
