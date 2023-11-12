package com.w.oa3.utils;

import java.sql.*;
import java.util.ResourceBundle;

//注册驱动
//获取连接
//获取数据库操作对象 conn.prepareStatement
//执行sq语句
//处理查询结果集
//释放资源

public class DButil {

    //静态变量，类加载时执行
    //属性资源配置文件绑定
    private static ResourceBundle bundle=ResourceBundle.getBundle("resources.jdbc");
    //根据属性配置文件key获取value
    private static String driver=bundle.getString("driver");
    private static String url=bundle.getString("url");
    private static String user=bundle.getString("user");
    private static String password=bundle.getString("password");

    static {
        //注册驱动（只需要执行一次，放在静态代码块中，DButil类加载时执行）
        try {
//            Class.forName("con.mysql.jdbc.Driver");
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {

        //获取连接
        Connection conn=DriverManager.getConnection(url,user,password);
        return conn;
    }




    /**
     * 释放资源
     * @param conn 连接对象
     * @param ps   数据库操作对象
     * @param rs   结果集对象
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(rs != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(rs != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
