package com.gotkx.utils.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateIO {

    /**
     * 在电脑D盘下创建一个文件为HelloWorld.txt文件，判断他是文件还是目录，在创建一个目
     * 录IOTest,之后将HelloWorld.txt移动到IOTest目录下去；之后遍历IOTest这个目录下的文
     * 件
     */
    @Test
    public void test_1() {
        String sourceString = "sourceString";    //待写入字符串
        byte[] sourceByte = sourceString.getBytes();

        File file = new File("G:\\Temp\\HelloWorld.txt");
        file.delete();
        try {
            if (file.createNewFile()) {
                FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
                outStream.write(sourceByte);
                outStream.close();    //关闭文件输出流
                //outStream.flush();
                System.out.println("这是" + (file.isDirectory() ? "文件夹" : "文件"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file_2 = new File("G:\\Temp\\Test");
        file_2.mkdirs();
        System.out.println("移动" + (file.renameTo(new File(file_2 + File.separator + file.getName())) ? "成功" : "失败"));

    }

    void findFileAndFolder(List list, File file) {
        //写一个递归的方法，查询文件和文件夹
        File[] listFiles = file.listFiles();

        //如果是一个空的文件夹            //后面的不执行，直接返回
        if (listFiles == null) {
            return;
        }

        //如果文件夹有内容,遍历里面的所有文件（包括文件夹和文件），都添加到集合里面
        for (File file2 : listFiles) {
            //如果只是想要里面的文件或者文件夹或者某些固定格式的文件可以判断下再添加
            list.add(file2);
            //添加到集合后，在来判断是否是文件夹，再遍历里面的所有文件
            //方法的递归
            findFileAndFolder(list, file2);
        }

    }

    /**
     * 递归实现输入任意目录，列出文件以及文件夹
     */
    @Test
    public void test_2() throws IOException {
        //比如输入D盘
        File file = new File("G:\\Soft\\Nox\\bin\\video");
        if (file.exists() && file.isDirectory()) {
            List<File> list = new ArrayList<File>();

            findFileAndFolder(list, file);
            //System.out.println(list.size());
            //将相关信息接入到硬盘
            FileOutputStream outputStream = new FileOutputStream(new File("G:\\Temp\\Test\\2.txt"));

            for (File xxx : list) {
                outputStream.write((xxx.toString() + "\n").getBytes());
            }
            outputStream.close();
        }

    }

    @Test
    /**
     * 从指定文件里面读取数据
     */
    public void test_3() throws IOException {
        File file = new File("G:\\Temp\\Test\\2.txt");
        FileInputStream inputStream = new FileInputStream(file);
        //InputStreamReader streamReader = new InputStreamReader(inputStream, "utf-8");

        byte[] bytes = new byte[1024];
//        int read = inputStream.read(bytes);
//        inputStream.close();
//        System.out.println(new String(bytes));
        while (true) {
            int read = inputStream.read(bytes);
            if (read <= 0) {
                break;
            }
            //从哪开始，到哪结束
            System.out.println(new String(bytes, 0, read));
        }
    }

    @Test
    /**
     * 输入一个文件名和一个字符串,统计这个字符串在这个文件中出现的次数
     * 逻辑是 一边读，先找到第一个匹配的字符比如我 m
     * 然后再进入for循环 用 mp4的 length属性 为条件，再逐个匹配 接下来，是不是 为 p呢
     */
    public void test_4() throws IOException {
        File file = new File("G:\\Temp\\Test\\2.txt");
        FileReader fileReader = new FileReader(file);
        String s = new String("mp4");
        int count = 0, c;
        while ((c = fileReader.read()) != -1) {
            while (c == s.charAt(0)) {
                //System.out.println(s.charAt(0));
                for (int i = 1; i < s.length(); i++) {
                    c = fileReader.read();
                    //System.out.println(s.charAt(i));
                    if (c != s.charAt(i)) {
                        break;
                    }
                    if (i == s.length() - 1) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    @Test
    /**
     * 两个文件，从第一个读取 写入到第二个
     */
    public void test_5() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("G:\\Temp\\Test\\2.txt"));
        File file = new File("G:\\Temp\\Test\\3.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        int read = fileInputStream.read(bytes);
        if(read > 0){
            outputStream.write(bytes);
        }
    }

}
