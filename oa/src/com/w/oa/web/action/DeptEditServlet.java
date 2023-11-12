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

public class DeptEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取应用的跟路径
        String contextPath=request.getContextPath();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改部门</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1>修改部门</h1>");
        out.print("		<hr >");
        out.print("		<form action='"+contextPath+"/dept/modify' method='post'>");



        //获取部门编号
        String deptno=request.getParameter("deptno");

        //连接数据库，根据部门编号查询信息
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //获取连接
            conn= DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql="select dname,loc as location from dept where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs=ps.executeQuery();
            if (rs.next()) {
                String dname=rs.getString("dname");
                String location=rs.getString("location");
                out.print("                部门编号<input type='text' name='deptno' value='"+deptno+"' readonly/><br>");
                out.print("                部门名称<input type='text' name='dname' value='"+dname+"'/><br>");
                out.print("                部门位置<input type='text' name='loc' value='"+location+"'/><br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, rs);
        }

        out.print("			<input type='submit' value='修改'/><br>");
        out.print("		</form>");
        out.print("	</body>");
        out.print("</html>");

    }
}
