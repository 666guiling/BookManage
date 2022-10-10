package com.BookManage.service.Impl;

import com.BookManage.dao.AdminDao;
import com.BookManage.dao.BookDao;
import com.BookManage.dao.Impl.AdminDaoImpl;
import com.BookManage.dao.Impl.BookDaoImpl;
import com.BookManage.dao.Impl.UserDaoImpl;
import com.BookManage.dao.UserDao;
import com.BookManage.entity.bo.BookBo;
import com.BookManage.entity.bo.BorrowBo;
import com.BookManage.entity.bo.Page;
import com.BookManage.entity.bo.UserBo;
import com.BookManage.entity.po.Admin;
import com.BookManage.entity.po.Book;
import com.BookManage.entity.po.BookClassIfy;
import com.BookManage.entity.po.User;
import com.BookManage.entity.vo.RespBean;
import com.BookManage.service.AdminService;
import com.BookManage.utils.ImgUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

/*
@ClassName : AdminServiceImpl
@Author : 不会吧
@Date: 2022/9/27 11:01
@Description : 
*/
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public RespBean checkName(HttpServletRequest request, String username) {
        return null;
    }

    @Override
    public RespBean login(HttpServletRequest request, String username, String password) {
        long count = adminDao.checkUsername(username);
        int id = adminDao.queryIDByBean(new Admin(username, password));
        if (count <=0){
            return RespBean.respError("用户名错误");
        }
        if (id <= 0){
            return RespBean.respError("密码错误");
        }
        request.getSession().setAttribute("id",id);
        return RespBean.respSuccess("登录成功");
    }

    @Override
    public RespBean toUser(HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        List<Admin> admins = adminDao.queryUser(id);
        if (admins.size()==0){
            return RespBean.respError("查不到用户信息");
        }
        Admin admin;
        admin = new Admin(admins.get(0).getId(),admins.get(0).getAdminName(),admins.get(0).getPassword(),admins.get(0).getImgUrl());
        return RespBean.respSuccess(admin);
    }

    @Override
    public RespBean updateUser(HttpServletRequest request, String username, String phone, String sex, Part part) {
        return null;
    }

    @Override
    public RespBean queryUserByPwd(HttpServletRequest request, String password) {
        return null;
    }

    @Override
    public RespBean updatePwd(HttpServletRequest request, String password) {
        return null;
    }

    @Override
    public RespBean getBookBoListByBookName(String bookName) {
        return null;
    }

    @Override
    public RespBean getUserBoList(int pageNum,int pageSize) {
        //获取所有用户信息
        List<User> userList = userDao.getList((pageNum-1)*pageSize,pageSize);
        List<UserBo> userBos = new ArrayList();
        UserBo userBo;
        List<Book> bookList;
        //根据用户id查询借阅书籍对象
        for (int i = 0; i < userList.size(); i++) {
            userBo = new UserBo(userList.get(i));
            //根据用户id获取借阅书籍集合
            bookList =userDao.getBorrowListByUserId(userList.get(i).getId());
            //存放书籍集合到userBo对象中
            userBo.setBorrowList(bookList);
            //userBo存放到集合中
            userBos.add(userBo);
        }
        if (userBos.size() == 0){
            return RespBean.respError("获取失败");
        }
        Page page = new Page();
        page.setCurrentPage(pageNum);
        int counts = (int)bookDao.queryBookCounts();
        page.setTotalCount(counts);
        page.setPageSize(pageSize);
        page.setList(userBos);
        return RespBean.respSuccess(page);
    }

    //根据用户名模糊查询用户对象
    @Override
    public RespBean getUserBoListByUserName(String username) {
        //获取所有用户信息
        List<User> userList = userDao.getUserByUserName(username);
        List<UserBo> userBos = new ArrayList();
        //
        UserBo userBo;
        List<Book> bookList;
        //根据用户id查询借阅书籍对象
        for (int i = 0; i < userList.size(); i++) {
            userBo = new UserBo(userList.get(i));
            //根据用户id获取借阅书籍集合
            bookList =userDao.getBorrowListByUserId(userList.get(i).getId());
            //存放书籍集合到userBo对象中
            userBo.setBorrowList(bookList);
            //userBo存放到集合中
            userBos.add(userBo);
        }
        return RespBean.respSuccess(userBos);
    }

    @Override
    public RespBean updateAdmin(HttpServletRequest request, String adminName, Part part) {
        int id = (int)request.getSession().getAttribute("id");
        long l = 0 ;
        String img = ImgUtils.getUrl(request, part);
        if (img.equals("null")){
            l = adminDao.updateAdminById2(new Admin(id,adminName));
        }else {
            l = adminDao.updateAdminById(new Admin(id,adminName,img));
        }
        if (l == 0){
            return RespBean.respError("修改失败");
        }
        return RespBean.respSuccess("修改成功");
    }

    @Override
    public RespBean changePwd(HttpServletRequest request,String password) {
        int id = (int)request.getSession().getAttribute("id");
        long l = adminDao.updatePassword(id, password);
        if (l==0){
            return RespBean.respError("修改失败");
        }
        return RespBean.respSuccess("");
    }

    @Override
    public RespBean checkPwd(HttpServletRequest request, String password) {
        int id = (int)request.getSession().getAttribute("id");
        long l = adminDao.queryUserByPwd(id, password);
        if (l==0){
            return RespBean.respError("密码错误");
        }
        return RespBean.respSuccess("");
    }

    @Override
    public RespBean getBookBoList2(int pageNum,int pageSize) {
        List<Book> bookList = bookDao.getList2((pageNum-1)*pageSize,pageSize);
        ArrayList<BookBo> bookBos = new ArrayList<>();
        BookBo bookBo;
        List<BookClassIfy> bookClassIfyList;
        for (int i = 0; i < bookList.size(); i++) {
            bookBo = new BookBo(bookList.get(i));
            bookClassIfyList = bookDao.getListByBookClassID(bookList.get(i).getId());
            bookBo.setBookClassIfyList(bookClassIfyList);
            bookBos.add(bookBo);
        }
        Page page = new Page();
        page.setCurrentPage(pageNum);
        int counts = (int)bookDao.queryBookCounts();
        page.setTotalCount(counts);
        page.setPageSize(pageSize);
        page.setList(bookBos);
        return RespBean.respSuccess(page);
    }

    @Override
    public RespBean getBorrow() {
        List<BorrowBo> borrowBos = userDao.queryBorrow();
        return RespBean.respSuccess(borrowBos);
    }
}
