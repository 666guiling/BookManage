package com.BookManage.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * @author:Ran
 * @since:JDK 1.8
 * @Date:2022/9/15
 */
public class DBUtils {
    private static String className;
    private static String url;
    private static String username;
    private static String password;
    static{
        //给静态属性赋值
        Properties properties = new Properties();
        try {
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            className = properties.getProperty("className");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            //1.加载驱动
            Class.forName(className);
            //2.通过 DriverManager 对象获取连接对象
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    //关闭资源
    public static void closeAll(AutoCloseable ... autoCloseables){
        for (AutoCloseable autoCloseable : autoCloseables) {
            try {
                if(autoCloseable != null){
                    autoCloseable.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
