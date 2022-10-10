package com.BookManage.dao.Impl;

import com.BookManage.dao.BorrowDao;
import com.BookManage.entity.bo.BorrowBo;
import com.BookManage.entity.po.Borrow;
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
@ClassName : BorrowDaoImpl
@Author : 不会吧
@Date: 2022/10/6 22:34
@Description : 
*/
public class BorrowDaoImpl implements BorrowDao {
    @Override
    public long checkUsername(String username) {
        return 0;
    }

    @Override
    public int queryIDByBean(Object obj) {
        return 0;
    }

    @Override
    public List queryUser(int id) {
        return null;
    }

    @Override
    public long updateUser(User user) {
        return 0;
    }

    @Override
    public long queryUserByPwd(int id, String password) {
        return 0;
    }

    @Override
    public long updatePassword(int id, String password) {
        return 0;
    }

    @Override
    public long updateBorrow(int id) {
        return DBManger.commonUpdate("update borrow set is_borrow =0 where id= ?",id);
    }

    @Override
    public List<BorrowBo> queryBorrow(int userID) {
        ArrayList<BorrowBo> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        BorrowBo borrow = null;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement("SELECT borrow.id,username,book_name,is_borrow FROM borrow LEFT JOIN user ON user.id = borrow.user_id " +
                    "LEFT JOIN book ON borrow.book_id = book.id where user.id = ?");
            ps.setInt(1,userID);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String bookName = resultSet.getString(3);
                int status = resultSet.getInt(4);
                borrow = new BorrowBo();
                borrow.setId(id);
                borrow.setUsername(username);
                borrow.setBookName(bookName);
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

    @Override
    public long queryBorrowBYID(int id) {
        return (long)DBManger.commonQuery("select count(*) from borrow where id=? and is_borrow=1",id);
    }
}
