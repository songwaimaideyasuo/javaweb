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

@jakarta.servlet.annotation.WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookie
        //Cookie[]数组可能是null，或者长度一点大于0
        Cookie[] cookies=request.getCookies();
        String username=null;
        String password=null;
        if (cookies!=null){
            for (Cookie cookie:cookies) {
                String name=cookie.getName();
                if ("username".equals(name)){
                     username=cookie.getValue();
                }else if("password".equals(name)){
                     password=cookie.getValue();
                }
            }
        }

        //要在这里使用username和password变量
        if (username!=null && password!=null) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean success = false;
            try {
                conn = DButil.getConnection();
                String sql = "select * from t_user where username=? and password=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //登录成功
                    success = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DButil.close(conn, ps, rs);
            }
            if (success) {
                //获取session
                HttpSession session=request.getSession();
//                session.setAttribute("username", username);

                User user=new User(username,password);
                session.setAttribute("user", user);


                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
