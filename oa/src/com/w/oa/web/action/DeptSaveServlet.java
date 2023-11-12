package com.w.oa.web.action;

import com.w.oa.utils.DButil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptSaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String deptno=request.getParameter("deptno");
        String dname=request.getParameter("dname");
        String loc=request.getParameter("loc");
        int count=1;

        //连接数据库，根据部门编号查询信息
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            //获取连接
            conn= DButil.getConnection();
        String sql="insert into dept(deptno,dname,loc) value(?,?,?)";
        ps=conn.prepareStatement(sql);
        ps.setString(1, deptno);
        ps.setString(2, dname);
        ps.setString(3, loc);
        count=ps.executeUpdate();
    } catch (
    SQLException e) {
        e.printStackTrace();
    }finally {
        //释放资源
        DButil.close(conn, ps, null);
    }

        if (count==1){
            //保存成功，跳转到列表页面
            //request.getRequestDispatcher("/dept/list").forward(request, response);

            //这里最好使用重定向（浏览器发生一次全新的请求）
            //浏览器地址栏发送请求，这个是Get请求
            response.sendRedirect(request.getContextPath()+"/dept/list");

        }else {
            //request.getRequestDispatcher("/error.html").forward(request, response);

            response.sendRedirect(request.getContextPath()+"error.html");
        }
    }

}
