package com.gotkx.utils.producer_consumer.blockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{

    private volatile boolean isRunning = true;
    private BlockingQueue<Integer> queue;                        // 内存缓冲区
    private static AtomicInteger count = new AtomicInteger();    // 总数，原子操作
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int data;
        Random r = new Random();

        System.out.println("start producer id = " + Thread.currentThread().getId());
        try {
            while (isRunning) {
                // 模拟延迟
                Thread.sleep(r.nextInt(SLEEPTIME));

                // 往阻塞队列中添加数据
                data = count.incrementAndGet();               	// 构造任务数据
                System.out.println("producer " + Thread.currentThread().getId() + " create data：" + data
                        + ", size：" + queue.size());
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println("failed to put data：" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
        }
    }

    public void stop() {
        isRunning = false;
    }

}
