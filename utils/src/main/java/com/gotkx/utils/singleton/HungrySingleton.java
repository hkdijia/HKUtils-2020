package com.gotkx.utils.singleton;

/**
 * 饿汉单例
 */
public class HungrySingleton {

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {

    }

    public static  HungrySingleton getInstance(){
        return hungrySingleton;
    }

    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance1 = HungrySingleton.getInstance();
        System.out.println(instance.equals(instance));
    }

}
