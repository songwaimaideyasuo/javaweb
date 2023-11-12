package com.w.javaweb.servlet;
        import javax.servlet.Servlet;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.ServletConfig;
        import java.io.IOException;
        import java.sql.*;
        import java.io.PrintWriter;

public class StudentServlet implements Servlet{

    public void init(ServletConfig config) throws ServletException{

    }

    public void service(ServletRequest request,ServletResponse response)
            throws ServletException , IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        try {
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.链接数据库
            String url = "jdbc:mysql://localhost:3307/book?useUnicode = true＆characterEncoding = utf-8＆useSSL = false&serverTimezone = GMT";
            Connection conn = DriverManager.getConnection(url,"root","123456");
            //3.获取statement对象
            Statement statement = conn.createStatement();
            String sql = "select * from users";
            //4.获取结果集
            ResultSet resultSet = statement.executeQuery(sql);
            //5.从结果集中获取用户名密码
            String username = "";
            String password = "";
            while(resultSet.next()){
                username = resultSet.getString("username");
                password = resultSet.getString("password");

                //System.out.println("username:" + username + "   " + "password:" + password);
                out.print(username + "," + password + "<br>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void destroy(){

    }

    public String getServletInfo(){
        return "";
    }

    public ServletConfig getServletConfig(){
        return null;
    }

}