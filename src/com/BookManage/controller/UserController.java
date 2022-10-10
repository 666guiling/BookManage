package com.BookManage.controller;

import com.BookManage.entity.vo.RespBean;
import com.BookManage.service.Impl.UserServiceImpl;
import com.BookManage.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

/*
@ClassName : UserController
@Author : 不会吧
@Date: 2022/9/24 15:42
@Description : 
*/
@WebServlet("/UserController")
@MultipartConfig
public class UserController extends BaseServlet{
    private UserService userService = new UserServiceImpl();

    //验证用户名是否重复
    protected RespBean checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        return userService.checkName(request,username);
    }

    //登录功能
    protected RespBean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return userService.login(request,username,password);
    }

    //注册
    protected RespBean register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        Part part = request.getPart("file");
        return userService.addUser(request,username,password,phone,sex,part);
    }

    //退出登录
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("id","");
        response.sendRedirect("login.html");
    }

    //展示用户个人信息
    protected RespBean toUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return userService.toUser(request);
    }

    //修改个人信息
    protected RespBean update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        Part part = request.getPart("file");
        return userService.updateUser(request,username,phone,sex,part);
    }

    //验证密码是否正确
    protected RespBean checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        return userService.queryUserByPwd(request,password);
    }

    //修改密码
    protected RespBean rPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        return userService.updatePwd(request,password);
    }

    //借阅图书
    protected RespBean borrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookID = Integer.parseInt(request.getParameter("id"));
        return userService.borrowBook(request,bookID);
    }

    //查看借阅信息
    protected RespBean queryBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return userService.queryBorrow(request);
    }

    //用户还书
    protected RespBean returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        return userService.returnBook(id);
    }
}
