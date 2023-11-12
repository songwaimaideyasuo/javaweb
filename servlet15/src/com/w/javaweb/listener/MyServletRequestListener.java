package com.w.javaweb.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

@jakarta.servlet.annotation.WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request对象销毁了");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request对象初始化了");
    }
}
