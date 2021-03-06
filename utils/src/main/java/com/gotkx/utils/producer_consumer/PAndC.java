package com.gotkx.utils.producer_consumer;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PAndC {

    public static void main(String[] args) throws InterruptedException{
        // 1.构建内存缓冲区
        Vector sharedQueue = new Vector();
        int size = 8;


        // 2.建立线程池和线程
        ExecutorService service = Executors.newCachedThreadPool();
        Producer prodThread1 = new Producer(sharedQueue, size);
        Producer prodThread2 = new Producer(sharedQueue, size);
        Producer prodThread3 = new Producer(sharedQueue, size);
        Consumer consThread1 = new Consumer(sharedQueue, size);
        Consumer consThread2 = new Consumer(sharedQueue, size);
        Consumer consThread3 = new Consumer(sharedQueue, size);
        service.execute(prodThread1);
        service.execute(prodThread2);
        service.execute(prodThread3);
        service.execute(consThread1);
        service.execute(consThread2);
        service.execute(consThread3);

        // 3.睡一会儿然后尝试停止生产者
        Thread.sleep(3 * 1000);
        prodThread1.stop();
        prodThread2.stop();
        prodThread3.stop();


        // 4.再睡一会儿关闭线程池
        Thread.sleep(3000);
        service.shutdown();
    }

}
