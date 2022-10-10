package com.BookManage.controller;

import com.BookManage.entity.vo.RespBean;
import com.BookManage.service.AdminService;
import com.BookManage.service.BookService;
import com.BookManage.service.Impl.AdminServiceImpl;
import com.BookManage.service.Impl.BookServiceImpl;
import com.BookManage.service.Impl.UserServiceImpl;
import com.BookManage.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/*
@ClassName : AdminController
@Author : 不会吧
@Date: 2022/9/27 10:59
@Description : 
*/
@WebServlet("/AdminController")
@MultipartConfig
public class AdminController extends BaseServlet{
    private AdminService adminService = new AdminServiceImpl() ;
    private BookService bookService = new BookServiceImpl();
    private UserService userService = new UserServiceImpl();

    //管理员登录
    protected RespBean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return adminService.login(request,username,password);
    }

    //退出登录
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("userID","");
        response.sendRedirect("login.html");
    }

    //获取所有用户信息
    protected RespBean getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        return adminService.getUserBoList(pageNum,pageSize);
    }

    //展示被修改图书信息
    protected RespBean toUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        return userService.queryUserById(id);
    }

    //删除图书（伪删除）
    protected RespBean delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        return userService.delUser(id);
    }

    //修改图书信息
    protected RespBean updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        Part part = request.getPart("file");
        int id = Integer.parseInt(request.getParameter("id"));
        return userService.updateUserByID(request,id,username,phone,sex,part);
    }

    //展示管理员用户个人信息
    protected RespBean toUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return adminService.toUser(request);
    }

    //展示用户个人信息
    protected RespBean toUser1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        return userService.queryUserById(id);
    }

    //展示被修改图书信息
    protected RespBean toBook1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        return bookService.queryBook(id);
    }

    //根据用户模糊查询用户信息
    protected RespBean getUserBoListByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        return adminService.getUserBoListByUserName(username);
    }

    //查看所有图书
    protected RespBean getBookList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        return adminService.getBookBoList2(pageNum,pageSize);
    }

    //根据书名模糊查询书籍
    protected RespBean getBookBoListByBookName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        return bookService.getBookBoListByBookName(bookName);
    }

    //添加图书信息
    protected RespBean addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String detail = request.getParameter("detail");
        String str = request.getParameter("counts");
        int counts = Integer.parseInt(str);
        String str2 = request.getParameter("bookClassId");
        int bookClassId = Integer.parseInt(str2);
        String author = request.getParameter("author");
        Part part = request.getPart("file");
        return bookService.addBook(request,bookName,detail,counts,bookClassId,author,part);
    }

    //验证图书名是否重复
    protected RespBean checkBookName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        return bookService.checkBookName(request,bookName);
    }

    //展示被修改图书信息
    protected RespBean toBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        return bookService.queryBook(id);
    }

    //删除图书（伪删除）
    protected RespBean delBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        return bookService.delBook(id);
    }

    //修改图书信息
    protected RespBean updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String detail = request.getParameter("detail");
        String str = request.getParameter("counts");
        int counts = Integer.parseInt(str);
        String str1 = request.getParameter("bookClassId");
        int bookClassId = Integer.parseInt(str1);
        String author = request.getParameter("author");
        Part part = request.getPart("file");
        String str2 = request.getParameter("id");
        int id = Integer.parseInt(str2);
        return bookService.updateBook(request,id,bookName,detail,counts,bookClassId,author,part);
    }

    //查询用户借阅信息
    protected RespBean toBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return adminService.getBorrow();
    }

    //管理员个人信息修改
    protected RespBean updateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminName = request.getParameter("adminName");
        Part part = request.getPart("file");
        return adminService.updateAdmin(request,adminName,part);
    }

    //修改管理员账号密码
    protected RespBean rPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        return adminService.changePwd(request,password);
    }

    //验证密码
    protected RespBean checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        return adminService.checkPwd(request,password);
    }
}
