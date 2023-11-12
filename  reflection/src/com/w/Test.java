package com.w;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.println("请输入你要执行的类");
        String className = in.nextLine();
        System.out.println("请输入你要执行的方法的名称");
        String methodName=in.nextLine();

        Class clz = Class.forName(className);    //此时用户输入的这个类的信息就保存到了clz对象中
        Object o = clz.newInstance();    //创建该类的对象

        Method method = clz.getMethod(methodName, String.class);
        method.invoke(o, "大宝");

        Method method1 = clz.getMethod(methodName, null);
        method1.invoke(o, null);


        //在不使用反射前，我们的程序都是按照我们的代码顺序执行

        //获取Studnt类的信息
//        Class clz = Student.class;
//        //Class clz = Class.forName("com.w.Student");       //也可以这样写
//
//        Object o = clz.newInstance();
//
//        //获取Student类中无参的方法
//        Method show1 = clz.getMethod("show", null);
//
//        //获取Student类中带参的方法
//        Method show2 = clz.getMethod("show", String.class);
//
//        //获取方法的目的是让其执行
//        //方法执行有三要素：对象  方法  参数
//        show1.invoke(o, null);
//
//        show2.invoke(o,"大宝");


    }
}



//如果你要自己写框架：反射+注解