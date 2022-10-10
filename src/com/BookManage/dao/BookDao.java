package com.BookManage.dao;

import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.BookClassIfy;

import java.util.List;

/*
@ClassName : BookDao
@Author : 不会吧
@Date: 2022/9/28 10:32
@Description : 
*/
public interface BookDao {
    //根据书籍分类id查询分类信息
    List<BookClassIfy> getListByBookClassID(int BookClassID);

    List<Book> getBookByBookName(String bookName);

    int checkBookName(String bookName);

    long addBook(Book book);

    //根据id获取图书信息
    List<Book> queryBookById(int id);

    //根据id修改图书信息
    long updateBookByID(Book book);

    //根据id修改图书信息   当修改信息，不修改头像时
    long updateBookByID2(Book book);

    //根据id将图书is_delete修改为0 （伪删除）
    long UpdateBookByID(int id);

    long queryBookCounts();

    List<Book> getList2(int pageNum, int pageSize);

    //将图书数量减一 ,添加用户的借阅信息
    long updateBookCounts(int userID ,int bookID);

    //查询图书数量
    List<Book> queryBookCountsBYID( int bookID);
}
