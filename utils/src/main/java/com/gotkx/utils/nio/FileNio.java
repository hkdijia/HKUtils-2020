package com.gotkx.utils.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNio {

    @Test
    public void test_3() throws  Exception{
        File file = new File("basic.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("copy.txt");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        inputStreamChannel.transferTo(0, inputStreamChannel.size(), outputStreamChannel);

        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 读取文件
     * @throws Exception
     */

    @Test
    public void test_2() throws Exception {
        File file = new File("basic.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        fileChannel.read(buffer);
        System.out.println(new String(buffer.array()));

        fileInputStream.close();
    }

    /**
     * 创建文件
     * @throws Exception
     */

    @Test
    public void test_1() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("basic.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String str = "hello,nio";
        buffer.put(str.getBytes());
        buffer.flip();
        fileChannel.write(buffer);

        fileOutputStream.close();
    }

}
