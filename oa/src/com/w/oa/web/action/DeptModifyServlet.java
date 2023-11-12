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

public class DeptModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        //PrintWriter out=response.getWriter();

        //获取部门编号
        String deptno=request.getParameter("deptno");
        String dname=request.getParameter("dname");
        String loc=request.getParameter("loc");

        //连接数据库，根据部门编号查询信息
        Connection conn=null;
        PreparedStatement ps=null;
        int count=0;
        try {
            //获取连接
            conn= DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql="update dept set dname = ?, loc = ? where deptno = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, null);
        }

        if(count==1){
            //更新成功,跳转到部门列表页面
            //request.getRequestDispatcher("/dept/list").forward(request, response);

            response.sendRedirect(request.getContextPath()+"/dept/list");
            return;
        }
        //request.getRequestDispatcher("/error.html").forward(request, response);

        response.sendRedirect(request.getContextPath()+"/error.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
