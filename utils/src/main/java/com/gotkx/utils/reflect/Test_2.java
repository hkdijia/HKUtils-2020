package com.gotkx.utils.reflect;

import java.lang.reflect.Method;

public class Test_2 {
    public void func(String[] args) {
        System.out.println((args == null) ? "null" : args.length);
    }

    public static void main(String[] args) throws Exception {
        Test_2 obj = new Test_2();
        Method m = obj.getClass().getMethod("func", String[].class);
        //m.invoke(obj, new String[1]);  //1
//        m.invoke(obj, new Object[]{new String[]{"a", "b"}}); //2
//        m.invoke(obj, (Object) new String[]{"a", "b"}); //3
//        m.invoke(obj, new String[]{"a"});  //4
//        m.invoke(obj, new String[]{"a", "b"}); //5
//        m.invoke(obj, new String[2]);  //6
    }
}