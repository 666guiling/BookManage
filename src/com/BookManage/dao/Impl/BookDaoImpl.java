package com.BookManage.dao.Impl;

import com.BookManage.dao.BookDao;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.BookClassIfy;
import com.BookManage.utils.DBManger;
import com.BookManage.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
@ClassName : BookDaoImpl
@Author : 不会吧
@Date: 2022/9/28 10:32
@Description : 
*/
public class BookDaoImpl implements BookDao {
    @Override
    public List<BookClassIfy> getListByBookClassID( int bookClassId) {
        return DBManger.commonQuery("SELECT * from bookclassify where id in (select bookclass_id from book where id = ?)",BookClassIfy.class,bookClassId);
    }

    @Override
    public List<Book> getBookByBookName(String bookName) {
        return DBManger.commonQuery("select * from book where is_delete=1 and book_name like '%"+bookName+"%'",Book.class);
    }

    @Override
    public int checkBookName(String bookName) {
        return (int)DBManger.commonQuery("select * from book where is_delete =1 and book_name=?",bookName);
    }

    @Override
    public long addBook(Book book) {
        return DBManger.commonUpdate("insert into book(book_name,img_url,detail,counts,bookclass_id,author) values(?,?,?,?,?,?)",book.getBookName(),
                book.getImgUrl(),book.getDetail(),book.getCounts(),book.getBookclassId(),book.getAuthor());
    }

    @Override
    public List<Book> queryBookById(int id) {
        return DBManger.commonQuery("select * from book where is_delete = 1 and id=?",Book.class,id);
    }

    @Override
    public long updateBookByID(Book book) {
        return DBManger.commonUpdate("update book set book_name=?,img_url=?,detail=?,counts=?,bookclass_id=?,author=? where id=?",book.getBookName(),
                book.getImgUrl(),book.getDetail(),book.getCounts(),book.getBookclassId(),book.getAuthor(),book.getId());
    }
    //当用户不修改头像时
    @Override
    public long updateBookByID2(Book book) {
        return DBManger.commonUpdate("update book set book_name=?,detail=?,counts=?,bookclass_id=?,author=? where id=?",book.getBookName(),
                book.getDetail(),book.getCounts(),book.getBookclassId(),book.getAuthor(),book.getId());
    }

    @Override
    public long UpdateBookByID(int id) {
        return DBManger.commonUpdate("update book set is_delete=0 where id=?",id);
    }

    @Override
    public long queryBookCounts() {
        return (long)DBManger.commonQuery("select count(*) from book where is_delete=1");
    }

    @Override
    public List<Book> getList2(int pageNum, int pageSize) {
        return DBManger.commonQuery("select * from book where is_delete=1 limit ?,?",Book.class,pageNum,pageSize);
    }

    @Override
    public long updateBookCounts(int userID ,int bookID) {
        Connection connection = null;
        PreparedStatement ps = null;
        int i = 0;
        try {
            connection = DruidUtils.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("update book set counts=counts-1 where is_delete=1 and id=?");
            ps.setInt(1,bookID);
            i = ps.executeUpdate();
            if(i == 0){
                new SQLException();
            }

            //添加用户的借阅信息
            ps = connection.prepareStatement("insert into borrow (book_id,user_id) values(?,?)");
            ps.setInt(1,bookID);
            ps.setInt(2,userID);
            i = ps.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DruidUtils.closeAll(ps,connection);
        }
        return i;
    }

    @Override
    public List<Book> queryBookCountsBYID(int bookID) {
        return DBManger.commonQuery("select * from book where is_delete=1 and id =?",Book.class,bookID);
    }
}
