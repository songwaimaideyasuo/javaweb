package com.w.oa3.web.action;
import com.w.oa3.bean.User;
import com.w.oa3.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//servlet 负责业务处理
//jsp     数据展示
@jakarta.servlet.annotation.WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath=request.getServletPath();
        if ("/user/login".equals(servletPath)){
            doLogin(request,response);
        }else if("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean success=false;

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //连接数据库，根据部门编号查询信息
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "select * from t_user where username=? and password=?";
            //编译sql
            ps = conn.prepareStatement(sql);
            //给？传值
            ps.setString(1, username);
            ps.setString(2, password);
            //执行sql
            rs=ps.executeQuery();
            //这个结果集最多只有1条数据。
            if(rs.next()){
                //登录成功
                success=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DButil.close(conn, ps, null);
        }

        if (success){
            //获取session对象（这里的要求是：必须获取到session，没有session也要新建一个session对象）
            HttpSession session=request.getSession();
            session.setAttribute("username", username);


            User user=new User(username,password);
            session.setAttribute("user", user);


            //登录成功了，并且用户确实选择了十天内免登录功能
            String f=request.getParameter("f");
            if ("1".equals(f)){
                //创建Cookie对象储存登录名
                Cookie cookie=new Cookie("username", username);
                //创建Cookie对象储存密码
                Cookie cookie2=new Cookie("password", password);
                //设置cookie有效期为10天
                cookie.setMaxAge(60*60*24*10);
                cookie2.setMaxAge(60*60*24*10);
                //设置cookie的path（只要访问这个应用，浏览器一定要携带这两个cookie）
                cookie.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                //响应cookie给浏览器
                response.addCookie(cookie);
                response.addCookie(cookie2);
            }

            response.sendRedirect(request.getContextPath()+"/dept/list");

        }else {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }

    }


    protected void doExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);
        if (session!=null){

            //从session域中移除user
            session.removeAttribute("user");

            //手动销毁session
            session.invalidate();
            //销毁cookie
            Cookie[] cookies=request.getCookies();
            if (cookies!=null){
                for (Cookie cookie:cookies) {
                        //设置cookie有效期为0，表示删除
                        cookie.setMaxAge(0);
                        //设置一下cookie的路径
                        cookie.setPath(request.getContextPath());   //项目下所有cookie都删除
                        //响应cookie给浏览器，浏览器会覆盖之前的cookie
                        response.addCookie(cookie);
                    }
                }
            }
            //跳转到登录页面
            response.sendRedirect(request.getContextPath());
        }
    }





