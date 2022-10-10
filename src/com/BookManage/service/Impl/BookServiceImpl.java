package com.BookManage.service.Impl;

import com.BookManage.dao.BookDao;
import com.BookManage.dao.Impl.BookDaoImpl;
import com.BookManage.entity.bo.BookBo;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.BookClassIfy;
import com.BookManage.entity.vo.RespBean;
import com.BookManage.service.BookService;
import com.BookManage.utils.ImgUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
@ClassName : BookServiceImpl
@Author : 不会吧
@Date: 2022/9/28 14:13
@Description : 
*/
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public RespBean checkName(HttpServletRequest request, String username) {
        return null;
    }

    @Override
    public RespBean login(HttpServletRequest request, String username, String password) {
        return null;
    }

    @Override
    public RespBean toUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public RespBean updateUser(HttpServletRequest request, String username, String phone, String sex, Part part) {
        return null;
    }

    @Override
    public RespBean queryUserByPwd(HttpServletRequest request, String password) {
        return null;
    }

    @Override
    public RespBean updatePwd(HttpServletRequest request, String password) {
        return null;
    }

    @Override
    public RespBean getBookBoListByBookName(String bookName) {
        List<Book> bookList = bookDao.getBookByBookName(bookName);
        ArrayList<BookBo> bookBos = new ArrayList<>();
        BookBo bookBo;
        List<BookClassIfy> bookClassIfyList;
        for (int i = 0; i < bookList.size(); i++) {
            bookBo = new BookBo(bookList.get(i));
            bookClassIfyList = bookDao.getListByBookClassID(bookList.get(i).getId());
            bookBo.setBookClassIfyList(bookClassIfyList);
            bookBos.add(bookBo);
        }
        return RespBean.respSuccess(bookBos);
    }

    @Override
    public RespBean checkBookName(HttpServletRequest request,String bookName) {
        int count = bookDao.checkBookName(bookName);
        List<Book> bookList = bookDao.queryBookById((int) request.getSession().getAttribute("id"));
        if (bookName != null){
            if (bookName.equals(bookList.get(0).getBookName())){
                return RespBean.respSuccess("");
            }
        }
        if (count >0){
            return RespBean.respError("图书重复，如有需要添加图书请修改数量");
        }
        return RespBean.respSuccess("");
    }

    @Override
    public RespBean addBook(HttpServletRequest request, String bookName, String detail, int counts, int bookClassId, String author, Part part) {
        String relativeParentPath = "/asserts/image";
        String realPath = request.getServletContext().getRealPath(relativeParentPath);
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String fileName = part.getSubmittedFileName();
        UUID uuid = UUID.randomUUID();
        fileName = uuid.toString()+"_"+ fileName;
        //目标文件的抽象对象
        File file1 = new File(realPath, fileName);
        try {
            //拷贝
            part.write(file1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = request.getScheme()+"://"+
                request.getServerName()+":"+
                request.getServerPort()+
                request.getContextPath()+
                relativeParentPath+"/"+fileName;
        long l = bookDao.addBook(new Book(bookName, imgUrl, detail, counts, bookClassId, author));
        if (l == 0){
            return RespBean.respError("添加失败");
        }
        return RespBean.respSuccess("添加成功");
    }

    @Override
    public RespBean queryBook(int id) {
        List<Book> bookList = bookDao.queryBookById(id);
        if (bookList.size() == 0){
            return RespBean.respError("查询失败");
        }
        Book book;
        book = new Book(bookList.get(0).getBookName(),bookList.get(0).getImgUrl(),bookList.get(0).getDetail(),
                bookList.get(0).getCounts(),bookList.get(0).getBookclassId(),bookList.get(0).getAuthor());
        return RespBean.respSuccess(book);
    }

    @Override
    public RespBean updateBook( HttpServletRequest request,int id,String bookName, String detail, int counts, int bookClassId, String author, Part part) {
        long l = 0;
        String url = ImgUtils.getUrl(request, part);
        if (url.equals("null")){
            l = bookDao.updateBookByID2(new Book(id, bookName, detail, counts,bookClassId, author));
        }else{
            l = bookDao.updateBookByID(new Book(id, bookName, url, detail,counts, bookClassId, author));
        }
        if (l==0){
            return RespBean.respError("修改失败");
        }
        return RespBean.respSuccess("修改成功");
    }

    @Override
    public RespBean delBook(int id) {
        long l = bookDao.UpdateBookByID(id);
        if (l == 0){
            return RespBean.respError("删除失败");
        }
        return RespBean.respSuccess("删除成功");
    }

    @Override
    public RespBean queryCounts() {
        long counts = bookDao.queryBookCounts();
        return RespBean.respSuccess(counts);
    }
}
