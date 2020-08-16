package com.gotkx.utils.producer_consumer;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private final Vector sharedQueue;
    private final int SIZE;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        SIZE = size;
    }

    @Override
    public void run() {
        int data;
        Random random = new Random();
        System.out.println("start producer id = " + Thread.currentThread().getName());

        try {
            while (isRunning){
                Thread.sleep(random.nextInt(SLEEPTIME));

                //队列满
                while(sharedQueue.size() == SIZE){
                    synchronized (sharedQueue){
                        System.out.println("Queue is full, producer " + Thread.currentThread().getName()
                                + " is waiting, size：" + sharedQueue.size());
                        sharedQueue.wait();
                    }
                }

                //队列不满
                synchronized (sharedQueue){
                    data = count.incrementAndGet();
                    sharedQueue.add(data);
                    System.out.println("producer create data 生产的数据:" + data + ", 生产队列size：" + sharedQueue.size());
                    //唤醒等待线程
                    sharedQueue.notifyAll();
                }


            }
        } catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupted();
        }
    }

    public void stop(){
        isRunning = false;
    }


}
