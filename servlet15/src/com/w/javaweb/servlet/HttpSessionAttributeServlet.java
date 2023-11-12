package com.w.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@jakarta.servlet.annotation.WebServlet("/session/attribute/test")
public class HttpSessionAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();

        session.setAttribute("user", "zhangsan");
        //替换
        session.setAttribute("user", "list");
        //删除
        session.removeAttribute("user");
    }
}
