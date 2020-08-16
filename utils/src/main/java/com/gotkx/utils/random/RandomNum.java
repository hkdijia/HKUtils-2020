package com.gotkx.utils.random;

import java.util.Random;

/**
 * 生成一个随机四位数，每位数字不重复。
 */
public class RandomNum {

    public static void main(String[] args) {
        generateNum();
    }

    private static void generateNum() {
        Random random = new Random();
        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        StringBuffer stringBuffer = new StringBuffer();
        int tmp = 0;
        while (stringBuffer.length() < 4) {
            tmp = random.nextInt(10);//0-9的随机数
            if (arr[tmp] == 0) {
                stringBuffer.append(tmp);
                arr[tmp] = 1;
            }
        }
        System.out.println(stringBuffer);
    }

}
