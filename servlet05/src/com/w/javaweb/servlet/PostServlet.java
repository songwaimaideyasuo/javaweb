package com.w.javaweb.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PostServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //相应一些内容到浏览器
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.print("<!doctype html>");
        out.print("<html>");
        out.print("     <head>");
        out.print("         <title>from post servlet</title>");
        out.print("     </head>");
        out.print("     <body>");
        out.print("         <h1>from post servlet</h1>");
        out.print("     </body>");
        out.print("</html>");
    }
}
