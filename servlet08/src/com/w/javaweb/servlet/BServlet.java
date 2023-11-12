package com.w.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户在请求域当中绑定的数据
        Object sysTime=req.getAttribute("sysTime");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        out.print("系统当前时间是："+sysTime);

    }

}
