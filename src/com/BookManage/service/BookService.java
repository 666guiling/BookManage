package com.BookManage.service;

import com.BookManage.entity.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/*
@ClassName : BookService
@Author : 不会吧
@Date: 2022/9/28 14:13
@Description : 
*/
public interface BookService extends BaseService{
    //验证图书名称是否重复
    RespBean checkBookName(HttpServletRequest request,String bookName);

    //添加图书信息
    RespBean addBook(HttpServletRequest request, String bookName, String detail, int counts, int bookClassId, String author, Part part);

    //根据id查询图书信息
    RespBean queryBook(int id);

    //修改图书信息
    RespBean updateBook(HttpServletRequest request,int id,String bookName, String detail, int counts, int bookClassId, String author, Part part);

    RespBean delBook(int id);

    RespBean queryCounts();

}
