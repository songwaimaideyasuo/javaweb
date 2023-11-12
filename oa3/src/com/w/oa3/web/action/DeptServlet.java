package com.w.oa3.web.action;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;
import com.w.oa3.bean.Dept;
import com.w.oa3.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@jakarta.servlet.annotation.WebServlet({"/dept/list","/dept/save","/dept/edit","/dept/detail","/dept/delete","/dept/modify"})
public class DeptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //入口的验证
//        request.getSession(false);
//        HttpSession session=request.getSession(false);
//        if (session!=null && session.getAttribute("username")!=null) {
//            String servletPath = request.getServletPath();
//            if ("/dept/list".equals(servletPath)) {
//                doList(request, response);
//            } else if ("/dept/detail".equals(servletPath)) {
//                doDetail(request, response);
//            } else if ("/dept/delete".equals(servletPath)) {
//                doDel(request, response);
//            } else if ("/dept/save".equals(servletPath)) {
//                doSave(request, response);
//            } else if ("/dept/modify".equals(servletPath)) {
//                doModify(request, response);
//            }
//        }else {
//            response.sendRedirect(request.getContextPath()+"/index.jsp");//服务web站点的根即可，自动找到欢迎页面
//        }


        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(request, response);
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");

        //获取部门编号
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        //连接数据库，根据部门编号查询信息
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            //获取连接
            conn = DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "update dept set dname = ?, loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DButil.close(conn, ps, null);
        }

        if (count == 1) {
            //更新成功,跳转到部门列表页面
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }


    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, null);
        }

        if (count==1){
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            response.sendRedirect(request.getContextPath()+"error.html");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
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
            String sql="delete from dept where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, deptno);
            //返回值是：影响了数据库表中多少条记录。
            count=ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, null);
        }

        if (count==1){
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
    }


    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        //创建部门对象
        Dept dept=new Dept();
        //获取部门编号
        String deptno=request.getParameter("dno");

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

                //封装对象（创建豆子）

                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, rs);
        }

        //这个豆子只有一个，所以不需要袋子，只需要将这个咖啡豆放到request域当中即可
        request.setAttribute("dept", dept);

        //转发（不要重定向,因为要跳转到JSP做数据展示）
//        request.getRequestDispatcher("/detail.jsp").forward(request, response);

        String f=request.getParameter("f");
        if("edit".equals(f)){
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        }else if("detail".equals(f)){
            request.getRequestDispatcher("/detail.jsp").forward(request, response);
        }

//        String forword="/" + request.getParameter("f") + ".jsp";
//        request.getRequestDispatcher(forword).forward(request, response);

    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        //准备一个容器，用来专门存储部门
        List<Dept> depts = new ArrayList();


        //连接数据库，查询所有部门
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //获取连接
            conn= DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql="select deptno as deptno,dname,loc from dept";
            ps=conn.prepareStatement(sql);
            //执行sql语句
            rs=ps.executeQuery();
            //处理结果集

            while (rs.next()){
                String deptno=rs.getString("deptno");
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");

                //将以上的零散的数据封装成java对象
                Dept dept=new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                //将部门对象放到list集合
                depts.add(dept);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, rs);
        }

        //将第一个集合放到请求域当中
        request.setAttribute("deptList", depts);

        //转发（不要重定向）
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }

}
