package com.BookManage.dao;

import com.BookManage.entity.bo.BorrowBo;
import com.BookManage.entity.po.Borrow;
import com.BookManage.service.BaseService;

import java.util.List;

/*
@ClassName : BorrowDao
@Author : 不会吧
@Date: 2022/10/6 22:34
@Description : 
*/
public interface BorrowDao extends BaseDao {
    //修改借阅表
    long updateBorrow(int id);

    //查询借阅表
    List<BorrowBo> queryBorrow(int userID);

    //根据借阅id查询借阅表
    long queryBorrowBYID(int id);
}
