package com.example._exercise.exercise_concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 下面的程序是 Java 提供的生产者、消费者模式的阻塞式的一种实现。
 * LinkedBlockingQueue：是一个底层用单向链表实现，可以是有界的也可以是无界的（Integer.MAX_VALUE）阻塞队列，
 * 采用 ReentrantLock 来控制并发，添加采用的是 putLock，移除采用的则是 takeLock，使用两个独占锁来控制消费和生产。
 * 其主要的方法为：
 * 1.取数据：
 * take()：首选，当队列为空时阻塞。
 * poll()：弹出队顶元素，队列为空时，返回空。
 * peek()：和 poll 类似，返回队顶元素，但顶元素不弹出。队列为空时返回 null。
 * remove(Object o)：移除某个元素，队列为空时抛出异常。成功移除返回 true。
 * 2.添加数据：
 * put()：首选，队满时阻塞。
 * add()：插入元素到队尾，插入成功返回 true，没有可用空间抛出异常 IllegalStateException。
 * offer()：队满时返回 false。（插入元素到队尾，插入成功返回 true，否则返回 false。）
 * <p>
 * 3.判断队列是否为空：size() 方法会遍历整个队列，时间复杂度为 O(n)，所以最好选用 isEmtpy()。
 */
public class _05_T_LinkedBlockingQueue {

    static BlockingQueue<String> lBQ = new LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    //put()：首选，队满时阻塞。
                    lBQ.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {//是循环，for(;;) 指令少，不占用寄存器，而且没有判断跳转，比 while(xx) 好。
                    try {
                        System.out.println(Thread.currentThread().getName() + " take -" + lBQ.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }
}
