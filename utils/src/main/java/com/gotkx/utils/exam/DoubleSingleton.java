package com.gotkx.utils.exam;

public class DoubleSingleton {

    private volatile static DoubleSingleton doubleSingleton;

    private DoubleSingleton(){};

    public static DoubleSingleton getInstance(){
        if(doubleSingleton == null){
            synchronized (DoubleSingleton.class){
                if(doubleSingleton == null){
                    doubleSingleton = new DoubleSingleton();
                }
            }
        }

        return doubleSingleton;
    }

    public static void main(String[] args) {
        DoubleSingleton instance = getInstance();
        DoubleSingleton instance1 = getInstance();
        System.out.println((instance == instance1) && (instance.equals(instance1)));
    }

}
