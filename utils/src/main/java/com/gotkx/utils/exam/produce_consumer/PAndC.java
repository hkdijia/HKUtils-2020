package com.gotkx.utils.exam.produce_consumer;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PAndC {

    public static void main(String[] args) throws InterruptedException {
        Vector vector = new Vector();
        int size = 8;

        ExecutorService pool = Executors.newCachedThreadPool();
        Producer producer_1 = new Producer(vector, size);
        Producer producer_2 = new Producer(vector, size);
        Producer producer_3 = new Producer(vector, size);

        Consumer consumer_1 = new Consumer(vector, size);
        Consumer consumer_2 = new Consumer(vector, size);
        Consumer consumer_3 = new Consumer(vector, size);

        pool.execute(producer_1);
        pool.execute(producer_2);
        pool.execute(producer_3);

        pool.execute(consumer_1);
        pool.execute(consumer_2);
        pool.execute(consumer_3);

        Thread.sleep(3 * 1000);
        producer_1.stop();
        producer_2.stop();
        producer_3.stop();

        Thread.sleep(3 * 1000);
        pool.shutdown();

    }

}
