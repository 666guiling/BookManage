package com.BookManage.entity.bo;

import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.BookClassIfy;

import java.util.List;

/*
@ClassName : BookBo
@Author : 不会吧
@Date: 2022/9/27 20:42
@Description : 
*/
public class BookBo {
    private Book book;
    private List<BookClassIfy> bookClassIfyList;

    public BookBo(Book book, List<BookClassIfy> bookClassIfyList) {
        this.book = book;
        this.bookClassIfyList = bookClassIfyList;
    }

    public BookBo(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<BookClassIfy> getBookClassIfyList() {
        return bookClassIfyList;
    }

    public void setBookClassIfyList(List<BookClassIfy> bookClassIfyList) {
        this.bookClassIfyList = bookClassIfyList;
    }

    @Override
    public String toString() {
        return "BookBo{" +
                "book=" + book +
                ", bookClassIfyList=" + bookClassIfyList +
                '}';
    }
}
