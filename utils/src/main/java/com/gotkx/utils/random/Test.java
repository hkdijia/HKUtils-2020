package com.gotkx.utils.random;

import java.util.Random;

public class Test {

    private static final int SLEEPTIME = 1000;

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(SLEEPTIME));
    }

}
