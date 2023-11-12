package com.w.oa3.web.filter;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;



public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        /**
         * 什么情况下不能拦截
         *
         *    用户访问index.jsp
         *    用户已经登录了
         *    用户要去登录
         *    WelcomServlet也不能
         */

        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;

        String servletPath=request.getServletPath();

        HttpSession session=request.getSession(false);

//        if ( "/index.jsp".equals(servletPath) || "/welcome".equals(servletPath) ||
//                "/user/login".equals(servletPath) || "/user/exit".equals(servletPath) ||
//                (session!=null && session.getAttribute("username")!=null)) {

        if ( "/index.jsp".equals(servletPath) || "/welcome".equals(servletPath) ||
                "/user/login".equals(servletPath) || "/user/exit".equals(servletPath) ||
                (session!=null && session.getAttribute("user")!=null)) {
           //继续往下走
            chain.doFilter(request, response);
        }else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");//服务web站点的根即可，自动找到欢迎页面
        }
    }
}
