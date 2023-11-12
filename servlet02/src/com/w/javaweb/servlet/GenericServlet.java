package com.w.javaweb.servlet;

import javax.servlet.*;
import java.io.IOException;

public abstract class GenericServlet implements Servlet {
    private ServletConfig config;

    @Override
    public final void init(ServletConfig config) throws ServletException {

        //System.out.println("xiaommmmm");
        this.config= config;
        this.init();
    }

    public void init() {
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }




    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
