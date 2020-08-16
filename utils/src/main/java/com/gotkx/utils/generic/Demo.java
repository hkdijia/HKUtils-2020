package com.gotkx.utils.generic;

/**
 * 其实这里有三个阶段
 *
 * 赋值空值  比如 null
 *
 * 然后执行 类的构造方法
 *
 * 最后在初始化阶段尾期，赋真实值
 *
 */
class SingleTon {

    private static SingleTon singleTon = new SingleTon();

    public static int count1 = 2;

    public static int count2 = 9;

    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}


public class Demo {
    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("count1=" + singleTon.count1);
        System.out.println("count2=" + singleTon.count2);
    }
}