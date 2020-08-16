package com.gotkx.utils.generic;

class Base {
    public static String s = "static_base";
    public String m = "base";
    static {
        System.out.println(s);
    }
    public static void staticTest() {
        System.out.println("base static: " + s);
    }
}

class Child extends Base {
    public static String s = "child_base";
    public String m = "child";
    public static void staticTest() {
        System.out.println("child static: " + s);
    }

}

public class Demo2 {
    public static void main(String[] args) {
        Child c = new Child();
//        Base b = c;
//        System.out.println(b.s);
//
//
//        System.out.println(b.m);
//        b.staticTest();
//
//        System.out.println(c.s);
//
//
//        System.out.println(c.m);
//        c.staticTest();
    }


}

