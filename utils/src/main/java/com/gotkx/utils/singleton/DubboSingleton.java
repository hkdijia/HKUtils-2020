package com.gotkx.utils.singleton;

/**
 * 双重校验锁实现单例
 */
public class DubboSingleton {

    private volatile static DubboSingleton dubboSingleton;

    private DubboSingleton(){};

    public static DubboSingleton getInstance(){
        if(dubboSingleton == null){
            synchronized (DubboSingleton.class){
                if(dubboSingleton == null){
                    dubboSingleton = new DubboSingleton();
                }
            }
        }
        return dubboSingleton;
    }

    public static void main(String[] args) {
        DubboSingleton instance = DubboSingleton.getInstance();
        DubboSingleton instance1 = DubboSingleton.getInstance();
        System.out.println(instance.equals(instance));
    }

}
