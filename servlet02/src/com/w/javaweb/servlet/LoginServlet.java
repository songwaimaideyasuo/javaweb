package com.w.javaweb.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class LoginServlet extends GenericServlet{


    public void init(){
        System.out.println("LoginServlet's init() method execute!");
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("正在处理用户登录请求，请稍后");

        ServletConfig config=this.getServletConfig();
        System.out.println("service方法是否可以获取ServletConfig对象？"+config);
    }
}
