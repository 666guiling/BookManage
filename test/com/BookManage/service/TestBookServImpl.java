package com.BookManage.service;

import com.BookManage.entity.po.Book;
import com.BookManage.entity.vo.RespBean;
import com.BookManage.service.Impl.AdminServiceImpl;
import com.BookManage.service.Impl.BookServiceImpl;
import org.junit.Test;

/*
@ClassName : TestBookServImpl
@Author : 不会吧
@Date: 2022/9/28 11:19
@Description : 
*/
public class TestBookServImpl {
    private AdminService adminService = new AdminServiceImpl();
    private BookService bookService = new BookServiceImpl();

    @Test
    public void method01(){
        RespBean respBean = bookService.queryBook(2);
        System.out.println(respBean.getData());
    }

    @Test
    public void method02(){
        //RespBean respBean = bookService.updateBook(1, "高中语文", "来背一篇孔雀东南飞吧少年", 20, 2, "人民教育出版社", null);
        //System.out.println(respBean.getMsg());
    }
}
