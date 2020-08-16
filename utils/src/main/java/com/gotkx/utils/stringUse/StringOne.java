package com.gotkx.utils.stringUse;

/**
 * 输入一字符串，若该字符串中间包含"*"，去掉"*"后输出；若该字符串首字符或尾字符为"*"，
 * 则保留该处的"*"；若该字符串不包含"*"或包含其他字符，可直接输出
 */

public class StringOne {

    public static void main(String[] args) {
        String str = new String("*Java* string split test*");
        System.out.println("main 方法："+doWork(str));
    }

    static String  doWork(String str){
        //将 str 切割成数组
//        String[] s = str.split(" ");
//        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
//        }
        //拿到字符串后，直接截取首尾，各第一个字符
        String start = str.substring(0, 1);
        String end = str.substring(str.length() - 1);
        //将剩余的字符，进行*的替换
        str = start + str.replace("*","") + end;
        //重新拼接字符串
        return str;
    }

}
