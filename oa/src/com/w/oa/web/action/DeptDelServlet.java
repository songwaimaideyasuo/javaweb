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

public class DeptDelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取部门编号
        String deptno=request.getParameter("deptno");

        //连接数据库，根据部门编号查询信息
        Connection conn=null;
        PreparedStatement ps=null;
        int count=0;

        try {
            //获取连接
            conn= DButil.getConnection();
            //获取预编译的数据库操作对象
            //开启事务（自动提交机制关闭）
            conn.setAutoCommit(false);
            String sql="delete from dept where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, deptno);
            //返回值是：影响了数据库表中多少条记录。
            count=ps.executeUpdate();
            //事务提交
            conn.commit();

        } catch (SQLException e) {
            //遇到异常要回滚
            if(conn != null){
                try{
                    conn.rollback();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, null);

        }

        if (count==1){
            /*
            删除成功，仍然跳转到部门列表页面
            部门列表页面的显示需要执行另一个Servlet，怎么办？转发。
             */
            //request.getRequestDispatcher("/dept/list").forward(request, response);

            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            //删除失败
            //request.getRequestDispatcher("/error.html").forward(request, response);

            response.sendRedirect(request.getContextPath()+"/error.html");
        }
    }
}
