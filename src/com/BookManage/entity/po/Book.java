package com.BookManage.entity.po;

/*
@ClassName : Book
@Author : 不会吧
@Date: 2022/9/24 14:36
@Description : 
*/

public class Book {
    private Integer id;
    private String bookName;
    private String imgUrl;
    private String detail;
    private Integer counts;
    private Integer bookclassId;
    private String author;

    public Book(Integer id, String bookName, String imgUrl, String detail, Integer counts, Integer bookclassId, String author) {
        this.id = id;
        this.bookName = bookName;
        this.imgUrl = imgUrl;
        this.detail = detail;
        this.counts = counts;
        this.bookclassId = bookclassId;
        this.author = author;
    }
    public Book( String bookName, String imgUrl, String detail, Integer counts, Integer bookclassId, String author) {
        this.bookName = bookName;
        this.imgUrl = imgUrl;
        this.detail = detail;
        this.counts = counts;
        this.bookclassId = bookclassId;
        this.author = author;
    }

    public Book( int id,String bookName, String detail, Integer counts, Integer bookclassId, String author) {
        this.id = id;
        this.bookName = bookName;
        this.detail = detail;
        this.counts = counts;
        this.bookclassId = bookclassId;
        this.author = author;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getBookclassId() {
        return bookclassId;
    }

    public void setBookclassId(Integer bookclassId) {
        this.bookclassId = bookclassId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", detail='" + detail + '\'' +
                ", counts=" + counts +
                ", bookclassId='" + bookclassId + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
