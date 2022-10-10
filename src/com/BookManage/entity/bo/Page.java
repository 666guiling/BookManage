package com.BookManage.entity.bo;

import java.util.List;

/*
@ClassName : Page
@Author : 不会吧
@Date: 2022/9/16 16:55
@Description : 
*/
public class Page {
    //当前页           --  前端发送
    private int currentPage;
    //数据总记录数        --  数据库获取
    private int totalCount;
    //总页数           ---  计算得出
    private int pageCount;
    //分页展示数据量   --  前端发送
    private int pageSize;
    //每页展示的记录       --      数据库获取
    private List<?> list;

    public Page() {
    }

    public Page(int currentPage, int totalCount, int pageCount, int pageSize, List<?> list) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageCount = pageCount;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (totalCount != 0 && pageSize!=0){
            this.pageCount = totalCount%pageSize ==0?totalCount/pageSize:totalCount/pageSize+1;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if(totalCount != 0 && pageSize != 0){
            this.pageCount = totalCount%pageSize ==0?totalCount/pageSize:totalCount/pageSize+1;
        }
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
