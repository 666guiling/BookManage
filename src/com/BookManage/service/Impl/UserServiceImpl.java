package com.BookManage.service.Impl;

import com.BookManage.dao.BookDao;
import com.BookManage.dao.BorrowDao;
import com.BookManage.dao.Impl.BookDaoImpl;
import com.BookManage.dao.Impl.BorrowDaoImpl;
import com.BookManage.dao.Impl.UserDaoImpl;
import com.BookManage.dao.UserDao;
import com.BookManage.entity.bo.BorrowBo;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.Borrow;
import com.BookManage.entity.po.User;
import com.BookManage.entity.vo.RespBean;
import com.BookManage.service.UserService;
import com.BookManage.utils.ImgUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/*
@ClassName : UserServiceImpl
@Author : 不会吧
@Date: 2022/9/24 15:05
@Description : 
*/
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private BorrowDao borrowDao = new BorrowDaoImpl();
    @Override
    public RespBean checkName(HttpServletRequest request,String username) {
        long count = userDao.checkUsername(username);
        List<User> users = userDao.queryUser((int) request.getSession().getAttribute("id"));
        if (username != null){
            if (username.equals(users.get(0).getUsername())){
                return RespBean.respSuccess("");
            }
        }
        if (count > 0){
            return RespBean.respError("用户名重复");
        }
        return RespBean.respSuccess("");
    }

    @Override
    public RespBean login(HttpServletRequest request, String username, String password) {
        int id = userDao.queryIDByBean(new User(username, password));
        long count = userDao.checkUsername(username);

        if (count <=0){
            return RespBean.respError("用户名错误");
        }
        if (id <= 0){
            return RespBean.respError("密码错误");
        }
        //登录成功将id存放到session中
        request.getSession().setAttribute("id",id);
        return RespBean.respSuccess("登录成功");
    }

    //根据从session中获取的id查询用户
    @Override
    public RespBean toUser(HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        List<User> users = userDao.queryUser(id);
        if (users.size()==0){
            return RespBean.respError("查不到用户信息");
        }
        User user;
        user = new User(users.get(0).getId(),users.get(0).getUsername(),users.get(0).getPassword(),users.get(0).getPhone(),users.get(0).getSex(),users.get(0).getImgUrl());
        return RespBean.respSuccess(user);
    }

    @Override
    public RespBean updateUser(HttpServletRequest request,String username,String phone, String sex,  Part part) {
        int id = (int)request.getSession().getAttribute("id");
        long l = 0;
        String url = ImgUtils.getUrl(request, part);
        if (url.equals("null")){
            l = userDao.updateUser2(new User(id,username,phone,sex));
        }else {
            l = userDao.updateUser( new User(id,username,phone,sex,url));
        }
        if (l==0){
            return RespBean.respError("修改失败");
        }
        return RespBean.respSuccess("修改成功");
    }

    //验证密码是否正确
    @Override
    public RespBean queryUserByPwd(HttpServletRequest request, String password) {
        int id = (int)request.getSession().getAttribute("id");
        long l = userDao.queryUserByPwd(id, password);
        if (l==0){
            return RespBean.respError("密码错误");
        }
        return RespBean.respSuccess("");
    }

    @Override
    public RespBean updatePwd(HttpServletRequest request, String password) {
        int id = (int)request.getSession().getAttribute("id");
        long l = userDao.updatePassword(id, password);
        if (l==0){
            return RespBean.respError("修改失败");
        }
        return RespBean.respSuccess("");
    }

    @Override
    public RespBean getBookBoListByBookName(String bookName) {
        return null;
    }

    @Override
    public RespBean addUser(HttpServletRequest request, String username, String password, String phone, String sex,  Part part) {
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
        User user = new User(username,password,phone,sex,imgUrl);
        int i = userDao.addUser(user);
        if (i ==0){
            return RespBean.respError("注册失败");
        }
        return RespBean.respSuccess("注册成功");
    }

    @Override
    public RespBean queryUserById(int id) {
        List<User> users = userDao.queryUserById(id);
        if (users.size()==0){
            return RespBean.respError("查不到用户信息");
        }
        User user;
        user = new User(users.get(0).getId(),users.get(0).getUsername(),users.get(0).getPassword(),users.get(0).getPhone(),users.get(0).getSex(),users.get(0).getImgUrl());
        return RespBean.respSuccess(user);
    }

    @Override
    public RespBean delUser(int id) {
        long l = userDao.UpdateUserByID(id);
        if (l == 0){
            return RespBean.respError("删除失败");
        }
        return RespBean.respSuccess("删除成功");
    }

    @Override
    public RespBean updateUserByID(HttpServletRequest request, int id, String username, String phone, String sex, Part part) {
        String url = ImgUtils.getUrl(request, part);
        long l = 0;
        if (url.equals("null")){
            l = userDao.updateUser2(new User(id,username,phone,sex));
        }else {
            l = userDao.updateUser( new User(id,username,phone,sex,url));
        }
        if (l==0){
            return RespBean.respError("修改失败");
        }
        return RespBean.respSuccess("修改成功");
    }

    @Override
    public RespBean borrowBook(HttpServletRequest request,int bookID) {
        int userID = (int)request.getSession().getAttribute("id");
        List<Book> bookList = bookDao.queryBookCountsBYID(bookID);
        int bookCounts = 0;
        for (Book book : bookList) {
            bookCounts = book.getCounts();
        }
        if (bookCounts <= 0){
            return RespBean.respError("图书库存不足");
        }
        long l = bookDao.updateBookCounts(userID, bookID);
        if (l <= 0 ){
            return RespBean.respError("借阅失败");
        }
        return RespBean.respSuccess("借阅成功");
    }

    @Override
    public RespBean queryBorrow(HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        List<BorrowBo> borrows = borrowDao.queryBorrow(id);
        return RespBean.respSuccess(borrows);
    }

    @Override
    public RespBean returnBook(int id) {
        long l1 = borrowDao.queryBorrowBYID(id);
        if (l1 <= 0){
            return RespBean.respError("已经归还图书，不用再次归还");
        }
        long l = borrowDao.updateBorrow(id);
        if (l <=0){
            return RespBean.respError("还书失败");
        }
        return RespBean.respSuccess("还书成功");
    }
}
