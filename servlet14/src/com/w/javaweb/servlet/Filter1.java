package com.w.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

//@jakarta.servlet.annotation.WebFilter({"/a.do","/b.do"})
public class Filter1 implements Filter {
    public Filter1() {
        System.out.println("无参构造方法执行");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init方法执行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("doFilter方法开始执行");
        //执行下一个过滤器，如果不是过滤器，则执行目标程序Servlet
        //向下走，要有它
        chain.doFilter(request,response);
        System.out.println("doFilter方法执行结束");
    }

    @Override
    public void destroy() {
        System.out.println("destroy方法执行");
    }
}
