package com.example._exercise.exercise_concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue：是一个不存储元素的阻塞队列。每一个 put 操作必须等待一个 take 操作，否则不能继续添加元素。
 * 其可以看成是一个传球手，负责把生产者线程处理的数据直接传递给消费者线程。
 * 队列本身并不存储任何元素，非常适合于传递性场景,比如在一个线程中使用的数据，传递给另外一个线程使用。
 * SynchronousQueue 的吞吐量高于 LinkedBlockingQueue 和 ArrayBlockingQueue。
 */
public class _09_T_SynchronusQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> sQ = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(sQ.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        sQ.put("aaa");
        System.out.println(sQ.size());
    }
}
