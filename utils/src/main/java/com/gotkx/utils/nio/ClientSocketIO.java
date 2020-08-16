package com.gotkx.utils.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientSocketIO {

    public static void main(String[] args) throws Exception{
        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);

        if(!channel.connect(address)){
            while (!channel.finishConnect()){
                System.out.println("非阻塞等待连接");
            }
        }

        String msg = "hello,Server";
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());

        channel.write(byteBuffer);

        System.in.read();
    }

}
