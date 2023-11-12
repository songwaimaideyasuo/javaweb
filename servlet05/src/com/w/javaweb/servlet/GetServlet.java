package com.w.javaweb.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("     <head>");
        out.println("         <title>from get servlet</title>");
        out.println("     </head>");
        out.println("     <body>");
        out.println("         <h1>from get servlet</h1>");
        out.println("     </body>");
        out.println("</html>");
    }
}
