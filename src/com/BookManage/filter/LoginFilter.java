package com.BookManage.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
@ClassName : ${NAME}
@Author : 不会吧
@Date: 2022/9/7 19:01
@Description : ${description}
*/

public class LoginFilter extends BaseFilter{

       public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.强转
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //2.处理乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //3.获取请求路径
        String uri = request.getRequestURI();
        //System.out.println("uri:"+uri);
        String action = request.getParameter("action");
        //4.未登录可以放行的请求
        if(uri.endsWith("login.html")||
                uri.endsWith("user/register.html") ||
                "login".equals(action) ||
                "register".equals(action) ||
                uri.endsWith("css/login.css")||
                uri.endsWith("asserts/js/login.js")||
                uri.endsWith("asserts/js/user/register.js")||
                uri.endsWith("css/index.css")||
                uri.endsWith("index.html")){
            //放行
            chain.doFilter(req, resp);
            return;
        }
        //5.登录后都可放行
        Object id = request.getSession().getAttribute("id");
        if(id != null && id !=""){
            //放行
            chain.doFilter(req, resp);
            return;
        }
        response.getWriter().write("<script>alert('请先登录');" +
                "location.href='login.html'</script>");
    }

}
