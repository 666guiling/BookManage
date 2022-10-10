package com.BookManage.entity.po;

/*
@ClassName : BorrowBo
@Author : 不会吧
@Date: 2022/9/27 15:22
@Description : 
*/
public class Borrow {
    private Integer id;
    private Integer bookId;
    private Integer userID;

    public Borrow(Integer id, Integer bookId, Integer userID) {
        this.id = id;
        this.bookId = bookId;
        this.userID = userID;
    }

    public Borrow(String username, String bookName) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "BorrowBo{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userID=" + userID +
                '}';
    }
}
