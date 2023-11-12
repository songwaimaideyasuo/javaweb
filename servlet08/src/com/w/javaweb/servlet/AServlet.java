package com.w.javaweb.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date nowTime=new Date();
        //将系统当前时间绑定到请求域当中
        req.setAttribute("sysTime", nowTime);

//        //取出来
//        Object sysTime=req.getAttribute("sysTime");
//        //输出到控制台
//        System.out.println(sysTime);

/*        //在AServlet中new一个BServlet对象，调用doGet方法，把request对象传过来
        //但是Servlet对象不能由程序员来new，自己new的生命周期不受猫管理
        BServlet bServlet=new BServlet();
        bServlet.doGet(req, resp);*/

        //转发
        //获取请求转发器对象
        RequestDispatcher dispatcher=req.getRequestDispatcher("/b");
        //调用转发器的forward方法完成转发
        dispatcher.forward(req, resp);
    }
}
