package com.gotkx.utils.exam.produce_consumer;

import java.util.Random;
import java.util.Vector;

public class Consumer implements Runnable {
    private final Vector shardQueue;
    private final int SIZE;
    private static final int SLEEPTIME = 1000;

    public Consumer(Vector shardQueue, int size) {
        this.shardQueue = shardQueue;
        SIZE = size;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("启动的消费者线程名字为：" + Thread.currentThread().getName());


        //延迟
        try {
            while (true) {
                Thread.sleep(random.nextInt(SLEEPTIME));

                //消费者而言。队列空 进行阻塞
                while (shardQueue.isEmpty()) {
                    synchronized (shardQueue) {
                        System.out.println("当前消费线程名称为：" + Thread.currentThread().getName() + ", 队列为空");
                        shardQueue.wait();
                    }
                }

                synchronized (shardQueue) {
                    System.out.println("当前消费线程名称为：" + Thread.currentThread().getName() + "被消费的值：" + shardQueue.remove(0)
                            + ", 消费后的队列长度:" + shardQueue.size());

                    shardQueue.notifyAll();

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
        }

    }
}
