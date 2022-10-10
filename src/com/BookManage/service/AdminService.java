package com.BookManage.service;

import com.BookManage.entity.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/*
@ClassName : AdminService
@Author : 不会吧
@Date: 2022/9/27 11:01
@Description : 
*/
public interface AdminService extends BaseService{
    //获取用户信息（包含借阅信息）
    RespBean getUserBoList(int pageNum,int pageSize);

    //根据用户名模糊查询用户对象
    RespBean getUserBoListByUserName(String username);

    /*//获取书籍信息（包括分类）
    RespBean getBookBoList();*/

    //修改个人信息
    RespBean updateAdmin(HttpServletRequest request, String adminName, Part part);

    //修改密码
    RespBean changePwd(HttpServletRequest request,String password);

    //验证密码
    RespBean checkPwd(HttpServletRequest request,String password);

    //获取书籍信息（包括分类）
    RespBean getBookBoList2(int pageNum,int pageSize);

    //获取用户借阅信息
    RespBean getBorrow();
}
