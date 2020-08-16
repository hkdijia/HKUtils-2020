package com.gotkx.utils.exam;

public class SingletonHungry {

    private static SingletonHungry singletonHungry = new SingletonHungry();

    private SingletonHungry(){};

    public static SingletonHungry getInstance(){
        return singletonHungry;
    }

    public static void main(String[] args) {
        SingletonHungry instance = getInstance();
        SingletonHungry instance1 = getInstance();
        System.out.println((instance == instance1) && (instance.equals(instance1)));
    }

}
