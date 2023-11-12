package com.w.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");//用来告诉用户的浏览器或相关设备，如何显示将要加载的数据，或者如何处理将要加载的数据页面的编码方式的。
        PrintWriter out=resp.getWriter();
        out.print("<h1>welcome</h1>");
    }
}
