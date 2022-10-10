package com.BookManage.dao;

import com.BookManage.entity.bo.BorrowBo;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.User;

import java.util.List;

/*
@ClassName : UserDaoImpl
@Author : 不会吧
@Date: 2022/9/24 14:52
@Description : 
*/
public interface UserDao extends BaseDao<User>{
    //添加用户
    int addUser(User user);

    //获取所有的用户信息
    List<User> getList(int pageNum,int pageSize);

    //根据用户id查询用户借阅情况
    List<Book> getBorrowListByUserId(int userId);

    //根据姓名模糊查询用户对象
    List<User> getUserByUserName(String username);

    long updateUser2(User user);

    List<User> queryUserById(int id);

    //修改is_delete
    long UpdateUserByID(int id);

    long queryUserCounts();

    long queryUsernameCounts(String username);

    List<BorrowBo> queryBorrow();

}
