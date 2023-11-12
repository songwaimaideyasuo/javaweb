package com.w.javaweb.listener;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@jakarta.servlet.annotation.WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

    //向session域当中存储数据时，以下方法被web服务器调用
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session data add");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session data Removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session data Replaced");
    }
}
