package com.BookManage.dao.Impl;

import com.BookManage.dao.UserDao;
import com.BookManage.entity.bo.BorrowBo;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.User;
import com.BookManage.utils.DBManger;
import com.BookManage.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
@ClassName : UserDaoImpl
@Author : 不会吧
@Date: 2022/9/24 14:52
@Description : 
*/
public class UserDaoImpl implements UserDao {

    //查询用户名
    @Override
    public long checkUsername(String username) {
        return (long)DBManger.commonQuery("select count(*) from user where is_delete=1 and username=?",username);
    }

    //根据账号密码查询id
    @Override
    public int queryIDByBean(User user) {
        return (int)DBManger.commonQuery("select id from user where is_delete =1 and username=? and password=?",user.getUsername(),user.getPassword());
    }

    @Override
    public List<User> queryUser(int id) {
        return DBManger.commonQuery("select * from user where is_delete=1 and id=?",User.class,id);

    }

    @Override
    public long updateUser(User user) {
        return DBManger.commonUpdate("update user set username=?,phone=?,sex=?,img_url=? where is_delete=1 and id=?",user.getUsername(),user.getPhone(),user.getSex(),user.getImgUrl(),user.getId());
    }

    @Override
    public long queryUserByPwd(int id, String password) {
        return (long)DBManger.commonQuery("select count(*) from user where is_delete=1 and id=? and password=?",id,password);
    }

    @Override
    public long updatePassword(int id, String password) {
        return (long)DBManger.commonUpdate("update user set password=? where is_delete=1 and id=?",password,id);
    }

    //添加用户
    @Override
    public int addUser(User user) {
        return DBManger.commonUpdate("insert into user (username,password,phone,sex,img_url) values(?,?,?,?,?)",user.getUsername(),user.getPassword(),user.getPhone(),user.getSex(),user.getImgUrl());
    }

    //查询所有用户
    @Override
    public List<User> getList(int pageNum,int pageSize) {
        return DBManger.commonQuery("select * from user where is_delete=1 limit ?,?",User.class,pageNum,pageSize);
    }

    //根据用户id查询用户借阅情况
    @Override
    public List<Book> getBorrowListByUserId(int userId) {
        return DBManger.commonQuery("select * from book where id in(select book_id from borrow where user_id=?)",Book.class,userId);
    }

    //根据姓名模糊查询用户对象
    @Override
    public List<User> getUserByUserName(String username) {
        return DBManger.commonQuery("select * from user where is_delete=1 and username like '%"+username+"%'",User.class);
    }

    @Override
    public long updateUser2(User user) {
        return DBManger.commonUpdate("update user set username=?,phone=?,sex=?where is_delete=1 and id=?",user.getUsername(),user.getPhone(),user.getSex(),user.getId());
    }

    @Override
    public List<User> queryUserById(int id) {
        return DBManger.commonQuery("select * from user where is_delete=1 and id=?",User.class,id);
    }

    @Override
    public long UpdateUserByID(int id) {
        return DBManger.commonUpdate("update user set is_delete=0 where id=?",id);
    }

    @Override
    public long queryUserCounts() {
        return (long)DBManger.commonQuery("select count(*) from user where is_delete=1");
    }

    @Override
    public long queryUsernameCounts(String username) {
        return (long)DBManger.commonQuery("select count(*) from user where is_delete=1 and username like '%"+username+"%'");
    }

    @Override
    public List<BorrowBo> queryBorrow() {
        ArrayList<BorrowBo> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        BorrowBo borrow = null;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement("SELECT username,book_name,user_id,book_id,is_borrow FROM borrow LEFT JOIN user ON user.id = borrow.user_id LEFT JOIN book ON borrow.book_id = book.id");
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString(1);
                String bookName = resultSet.getString(2);
                int anInt = resultSet.getInt(3);
                int anInt1 = resultSet.getInt(4);
                int status = resultSet.getInt(5);
                borrow = new BorrowBo(username,bookName,anInt,anInt1);
                borrow.setStatus(status);
                list.add(borrow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,ps,connection);
        }
        return list;
    }

}
