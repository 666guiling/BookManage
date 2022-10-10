package com.BookManage.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/*
@ClassName : BaseFilter
@Author : 不会吧
@Date: 2022/9/7 18:59
@Description : 
*/
public abstract class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
