package com.BookManage.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
@ClassName : DBManger
@Author : 不会吧
@Date: 2022/9/21 9:42
@Description : 
*/
public class DBManger {
    //更新数据库封装的操作
    public static int commonUpdate(String sql,Object ... objects){
        Connection connection = null;
        PreparedStatement ps =  null;
        int rSet = 0;
        try {
            connection =DruidUtils.getConnection();
            ps = connection.prepareStatement(sql);
            //循环给SQL语句里的占位符赋值
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i+1,objects[i]);
            }
            rSet = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            DruidUtils.closeAll(ps,connection);
        }
        return rSet;
    }


    public static <T> List<T> commonQuery(String sql,Class<T> tClass,Object... objects){
        Connection connection = null;
        PreparedStatement ps =  null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList();
        T t;
        int rSet = 0;
        try {
            connection =DruidUtils.getConnection();
            ps = connection.prepareStatement(sql);
            //先判断是否需要给占位符赋值
            if (objects != null){
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i+1,objects[i]);
                }
            }
            resultSet = ps.executeQuery();
            Field[] fields = tClass.getDeclaredFields();
            while(resultSet.next()){
                t = tClass.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object object = resultSet.getObject(changeFieldName(field.getName()));
                    field.set(t,object);
                }
                list.add(t);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally{
            DruidUtils.closeAll(resultSet,ps,connection);
        }
        return list;
    }

    //查询单个数据，返回查找的个数
    public static Object commonQuery(String sql,Object... objects){
        Connection connection = null;
        PreparedStatement ps =  null;
        ResultSet resultSet = null;
        Object num = -1;
        try {
            connection = DruidUtils.getConnection();
            ps = connection.prepareStatement(sql);
            if (objects != null){
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i+1,objects[i]);
                }
            }
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                num = resultSet.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            DruidUtils.closeAll(resultSet,ps,connection);
        }
        return num;
    }

    //驼峰命名规则转换成数据库命名规则
    private static String changeFieldName(String name){
        char[] chars = name.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])){
                sb.append("_");//在大写字母前面加一个下划线
                sb.append(Character.toLowerCase(chars[i]));
            }else{
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
