package com.gotkx.utils.exam.produce_consumer;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private final Vector shardQueue;
    private final int SIZE;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(Vector shardQueue, int size) {
        this.shardQueue = shardQueue;
        SIZE = size;
    }

    @Override
    public void run() {
        int data;
        Random random = new Random();
        System.out.println("消费者线程 名称：" + Thread.currentThread().getName());

        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEPTIME));
                //队列满
                while (shardQueue.size() == SIZE){
                    synchronized (shardQueue) {
                        System.out.println("生产者队列" + Thread.currentThread().getName() + "已经满了" + ", size 为" + shardQueue.size());
                        shardQueue.wait();
                    }
                }

                //队列不满
                synchronized (shardQueue){
                    data = count.incrementAndGet();
                    shardQueue.add(data);
                    System.out.println("生产者"+Thread.currentThread().getName()+"生产的内容:"+data+", 当前队列size为:" + shardQueue.size());
                    //唤醒
                    shardQueue.notifyAll();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
            //Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
