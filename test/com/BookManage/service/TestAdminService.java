package com.BookManage.service;

import com.BookManage.entity.vo.RespBean;
import com.BookManage.service.Impl.AdminServiceImpl;
import org.junit.Test;

/*
@ClassName : TestAdminService
@Author : 不会吧
@Date: 2022/9/27 17:20
@Description : 
*/
public class TestAdminService {
    private AdminService adminService = new AdminServiceImpl();
    @Test
    public void method1(){
        RespBean userBoList = adminService.getUserBoList(1,3);
        System.out.println(userBoList.getData());
    }
    @Test
    public void method2(){
//        RespBean name = adminService.getUserBoListByUserName("派",1,3);
//        System.out.println(name.getData());
    }

    @Test
    public void method02(){
        RespBean bookBoList2 = adminService.getBookBoList2(2, 3);
        System.out.println(bookBoList2.getData());
    }
}
