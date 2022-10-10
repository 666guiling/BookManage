package com.BookManage.dao.Impl;

import com.BookManage.dao.AdminDao;
import com.BookManage.entity.po.Admin;
import com.BookManage.entity.po.User;
import com.BookManage.utils.DBManger;

import java.util.List;

/*
@ClassName : AdminDaoImpl
@Author : 不会吧
@Date: 2022/9/27 11:00
@Description : 
*/
public class AdminDaoImpl implements AdminDao {
    @Override
    public long checkUsername(String username) {
        return (long)DBManger.commonQuery("select count(*) from admin where is_delete=1 and admin_name=?",username);
    }

    @Override
    public int queryIDByBean(Admin admin) {
        return (int) DBManger.commonQuery("select id from admin where is_delete=1 and admin_name=? and password=?",admin.getAdminName(),admin.getPassword());
    }

    @Override
    public List<Admin> queryUser(int id) {
        return DBManger.commonQuery("select * from admin where is_delete=1 and id=?",Admin.class,id);
    }

    @Override
    public long updateUser(User user) {
        return 0;
    }

    @Override
    public long queryUserByPwd(int id, String password) {
        return (long)DBManger.commonQuery("select count(*) from admin where is_delete=1 and id=? and password=?", id,password);
    }

    @Override
    public long updatePassword(int id, String password) {
        return DBManger.commonUpdate("update admin set password = ? where id = ?",password,id);
    }

    @Override
    public long updateAdminById(Admin admin) {
        return DBManger.commonUpdate("update admin set admin_name=? , img_url=? where is_delete=1 and id=?",admin.getAdminName(),admin.getImgUrl(),admin.getId());
    }

    @Override
    public long updateAdminById2(Admin admin) {
        return DBManger.commonUpdate("update admin set admin_name=? where is_delete=1 and id=?",admin.getAdminName(),admin.getId());
    }
}
