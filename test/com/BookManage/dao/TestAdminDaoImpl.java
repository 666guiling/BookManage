package com.BookManage.dao;

import com.BookManage.entity.po.Admin;
import org.junit.Test;

/*
@ClassName : TestAdminDaoImpl
@Author : 不会吧
@Date: 2022/9/27 13:58
@Description : 
*/
public class TestAdminDaoImpl {
    private AdminDao adminDao = new com.BookManage.dao.Impl.AdminDaoImpl();
    @Test
    public void method(){
        int id = adminDao.queryIDByBean(new Admin("admin", "admin"));
        System.out.println(id);
    }
    @Test
    public void method1(){
        long admin = adminDao.checkUsername("admin");
        int id = (int) admin;
        System.out.println(id);
    }

    @Test
    public void method2(){
        long admin = adminDao.updateAdminById2(new Admin(1, "admin"));
        System.out.println(admin);
    }
}
