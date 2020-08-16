package com.gotkx.utils.producer_consumer;

import java.util.Random;
import java.util.Vector;

/**
 * 消费者
 */
public class Consumer implements Runnable{

    private final Vector sharedQueue;
    private final int SIZE;
    private static final int SLEEPTIME = 1000;

    public Consumer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        SIZE = size;
    }


    @Override
    public void run() {
        Random random = new Random();
        System.out.println("start consumer id = " + Thread.currentThread().getName());

        try{
            while (true){
                //模拟延迟  随机的
                Thread.sleep(random.nextInt(SLEEPTIME));

                //当队列空  阻塞
                while (sharedQueue.isEmpty()){
                    synchronized (sharedQueue){
                        System.out.println("Queue is empty, consumer " + Thread.currentThread().getName()
                                + " is waiting, size：" + sharedQueue.size());
                        sharedQueue.wait();
                    }
                }

                //队列不空  持续消费
                synchronized (sharedQueue){
                    System.out.println("consumer consume data 被消费的值：" + sharedQueue.remove(0) + ", 消费后队列size：" + sharedQueue.size());
                    sharedQueue.notifyAll();
                }

            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}
