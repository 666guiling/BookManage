package com.BookManage.service;

import com.BookManage.entity.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/*
@ClassName : BaseService
@Author : 不会吧
@Date: 2022/9/24 15:07
@Description : 
*/
public interface BaseService {
    //验证用户名是否重复
    RespBean checkName(HttpServletRequest request,String username);

    //登录功能
    RespBean login(HttpServletRequest request, String username, String password);

    //展示单个用户信息
    RespBean toUser(HttpServletRequest request);

    //根据id修改用户信息
    RespBean updateUser(HttpServletRequest request,String username,String phone, String sex,  Part part);

    //根据id验证用户密码
    RespBean queryUserByPwd(HttpServletRequest request,String password);

    //根据id修改密码
    RespBean updatePwd(HttpServletRequest request,String password);

    //根据书名模糊查询书籍信息
    RespBean getBookBoListByBookName(String bookName);
}
