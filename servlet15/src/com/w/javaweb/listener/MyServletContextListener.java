package com.w.javaweb.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

//这个监听器主要监听：ServletContext对象的状态
@jakarta.servlet.annotation.WebListener
public class MyServletContextListener implements ServletContextListener {
    /**
     * 监听器中方法不需要程序员手动调用，发生某个特殊事件之后被服务器调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象销毁了");
    }
}
