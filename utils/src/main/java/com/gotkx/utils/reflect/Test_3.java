package com.gotkx.utils.reflect;

import java.lang.reflect.Method;

public class Test_3 {
    public void age(int age) {
        System.out.println("int age=" + age);
    }

    public void age(Integer age) {
        System.out.println("Integer age=" + age);
    }

    public static void main(String[] args) throws Exception {
        Test_3 obj = new Test_3();
        Method m1 = obj.getClass().getMethod("age", int.class);
        m1.invoke(obj, new Integer(27));  //1
        m1.invoke(obj, 28); //2
        Method m2 = obj.getClass().getMethod("age", Integer.class);
        m2.invoke(obj, new Integer(27));  //3
        m2.invoke(obj, 28); //4
    }
}