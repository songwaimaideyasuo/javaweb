package com.w.oa.web.action;

import com.w.oa.utils.DButil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title></title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1>部门详情</h1>");
        out.print("		<hr />");


        //获取部门编号
        String deptno=request.getParameter("fdsaf");

        //连接数据库，根据部门编号查询信息
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //获取连接
            conn= DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql="select dname,loc from dept where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs=ps.executeQuery();
            if (rs.next()) {
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                out.print("部门编号："+deptno+" <br>");
                out.print("部门名称："+dname+" <br>");
                out.print("部门位置："+loc+" <br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, rs);
        }

        out.print("		<input type='button' value='后退' onclick='window.history.back()'/>");
        out.print("	</body>");
        out.print("</html>");
    }
}
