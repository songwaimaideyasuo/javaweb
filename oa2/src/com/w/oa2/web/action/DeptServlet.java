package com.w.oa2.web.action;

import com.w.oa2.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/dept/list","/dept/save","/dept/edit","/dept/detail","/dept/modify"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if("/dept/list".equals(servletPath)){
            try {
                doList(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("/dept/Save".equals(servletPath)){
            try {
                doSave(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("/dept/edit".equals(servletPath)){
            try {
                doEdit(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("/dept/detail".equals(servletPath)){
            try {
                doDetail(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("/dept/modify".equals(servletPath)){
            try {
                doModify(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("/dept/delete".equals(servletPath)){
            try {
                doDel(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,Exception {
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
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            //删除失败
            response.sendRedirect(request.getContextPath()+"/error.html");
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException,Exception {
        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        //PrintWriter out=response.getWriter();

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
            //request.getRequestDispatcher("/dept/list").forward(request, response);

            response.sendRedirect(request.getContextPath() + "/dept/list");
            return;
        } else {
            //request.getRequestDispatcher("/error.html").forward(request, response);

            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException,Exception{
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

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException,Exception{
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

    private void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException,Exception{
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

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException,Exception{
        //获取应用的跟路径
        String contextPath=request.getContextPath();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>部门列表页面</title>");

        out.print("<script type='text/javascript'>");
        out.print("                function del(dno) {");
        out.print("            if (window.confirm('亲，删了不可恢复哦！')){");
        out.print("                document.location.href='/oa/dept/delete?deptno=' +dno;");
        out.print("            }");
        out.print("        }");
        out.print("</script>");

        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1 align='center'>部门列表</h1>");
        out.print("		<hr >");
        out.print("		<table border='1px' align='center' width='50%'>");
        out.print("		<tr>");
        out.print("			<th>序号</th>");
        out.print("			<th>部门编号</th>");
        out.print("			<th>部门名称</th>");
        out.print("			<th>操作</th>");
        out.print("		</tr>");


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
            int i=0;
            while (rs.next()){
                String deptno=rs.getString("deptno");
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");

                out.print("		<tr>");
                out.print("			<td>"+(++i)+"</td>");
                out.print("			<td>"+deptno+"</td>");
                out.print("			<td>"+dname+"</td>");
                out.print("			<td>");
                out.print("				<a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.print("				<a href='"+contextPath+"/dept/edit?deptno="+deptno+"'>修改</a>");
                out.print("				<a href='"+contextPath+"/dept/detail?fdsaf="+deptno+"'>详情</a>");
                out.print("			</td>");
                out.print("		</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DButil.close(conn, ps, rs);
        }

        out.print("		</table>");
        out.print("		<hr />");
        out.print("		<a href='"+contextPath+"/add.html'>新增部门</a>");
        out.print("	</body>");
        out.print("</html>");
    }
}
