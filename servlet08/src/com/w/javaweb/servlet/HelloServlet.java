package com.w.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(name = "hello" , urlPatterns = {"/d","/s"},loadOnStartup = 1)

public class HelloServlet extends HttpServlet{

    public HelloServlet() {
        System.out.println("无参数构造方法执行，加载成功");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
