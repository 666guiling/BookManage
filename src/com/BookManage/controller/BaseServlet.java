package com.BookManage.controller;

import com.BookManage.entity.vo.RespBean;
import com.alibaba.fastjson.JSON;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * @author:Ran
 * @since:JDK 1.8
 * @Date:2022/9/6
 */

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.处理乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //2.获取 action 值
        String action = request.getParameter("action");
        //3.获取当前 Servlet 的反射对象
        Class<? extends BaseServlet> clazz = this.getClass();
        try {
            //4.根据反射对象和 action 值获取方法对象
            Method method = clazz.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //5.打开访问权限
            method.setAccessible(true);
            //6.调用方法
            RespBean respBean = (RespBean)method.invoke(this, request, response);
            //7.把响应对象转换成 json 格式字符串再响应前端
            response.getWriter().write(JSON.toJSONString(respBean));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
