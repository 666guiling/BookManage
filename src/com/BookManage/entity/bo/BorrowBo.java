package com.BookManage.entity.bo;

/*
@ClassName : BorrowBo
@Author : 不会吧
@Date: 2022/9/30 14:53
@Description : 
*/
public class BorrowBo {
    private String username;
    private String bookName;
    private int userId;
    private int BookId;
    private int id;
    private int status;

    public BorrowBo(String username, String bookName) {
        this.username = username;
        this.bookName = bookName;
    }

    public BorrowBo(String username, String bookName, int userId, int bookId) {
        this.username = username;
        this.bookName = bookName;
        this.userId = userId;
        BookId = bookId;
    }

    public BorrowBo(String username, String bookName, int userId, int bookId, int id, int status) {
        this.username = username;
        this.bookName = bookName;
        this.userId = userId;
        BookId = bookId;
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BorrowBo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "BorrowBo{" +
                "username='" + username + '\'' +
                ", bookName='" + bookName + '\'' +
                ", userId=" + userId +
                ", BookId=" + BookId +
                '}';
    }
}
