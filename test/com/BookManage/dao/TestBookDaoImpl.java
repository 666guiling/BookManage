package com.BookManage.dao;

import com.BookManage.dao.Impl.BookDaoImpl;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.User;
import org.junit.Test;

import java.util.List;

/*
@ClassName : TestBookDaoImpl
@Author : 不会吧
@Date: 2022/9/28 10:52
@Description : 
*/
public class TestBookDaoImpl {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void method3(){
        int l = bookDao.checkBookName("语文");
        System.out.println(l);
    }
    @Test
    public void method04(){
        long l = bookDao.addBook(new Book("java","","从入门到入坟",10,3,"李白"));
    }
    @Test
    public void method05(){
        bookDao.updateBookByID2(new Book(1,"语文","来背一篇孔雀东南飞吧少年",20,1,"人民教育出版社"));
    }
    @Test
    public void method06(){
        List<Book> list2 = bookDao.getList2(2, 3);
        for (Book book : list2) {
            System.out.println(book);
        }
    }
}
