package com.w.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


/*
name   Servlet名字
urlPatterns       Servlet映射路径
loadOnStartup     服务器启动阶段是否加载该Servlet
urlPatterns和value属性一致
如果注解名属性是value，属性名可以省略
 */
@WebServlet(name = "hello" ,
        urlPatterns = {"/a","/b","/c"},
        //loadOnStartup = 1,
initParams = {@jakarta.servlet.annotation.WebInitParam(name = "username",value = "root"),@jakarta.servlet.annotation.WebInitParam(name = "password",value = "123")})
public class HelloServlet extends HttpServlet{

    public HelloServlet() {
        System.out.println("无参数构造方法执行，加载成功");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();

        //获取servle name
        String servletName=getServletName();
        out.print("servlet name="+servletName+"<br>");

        //获取servle path
        String servletPath=req.getServletPath();
        out.print("servlet path="+servletPath+"<br>");

        //获取初始化参数
        Enumeration<String> names=getInitParameterNames();
        while (names.hasMoreElements()){
            String name=names.nextElement();
            String value=getInitParameter(name);
            out.print(name+"="+value+"<br>");
        }
    }
}

