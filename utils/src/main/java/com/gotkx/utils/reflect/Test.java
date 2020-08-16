package com.gotkx.utils.reflect;

import java.lang.reflect.Method;

public class Test {
    public void func(String key, String[] args) {
        System.out.println((args == null) ? "null" : args.length);
    }

    public static void main(String[] args) throws Exception {
        Test obj = new Test();
        Method m = obj.getClass().getMethod("func", String.class, String[].class);
        m.invoke(obj, new String(), (Object)new String[1]);  //1

//        m.invoke(obj, new String(), new Object[]{new String[]{"a", "b"}}); //2
//        m.invoke(obj, new String(), (Object) new String[]{"a", "b"}); //3
//        m.invoke(obj, new String(), new String[]{"a"});  //4
//        m.invoke(obj, new String(), new String[]{"a", "b"}); //5
//        m.invoke(obj, new String(), new String[2]);  //6
    }
}