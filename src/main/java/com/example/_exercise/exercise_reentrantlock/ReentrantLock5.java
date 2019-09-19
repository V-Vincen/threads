package com.example._exercise.exercise_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 用于替代 Synchronized，本例中由于 m1 锁定 this，只有 m1 执行完毕的时候，m2 才能执行，
 * 这里是复习 synchronized 最原始的语义。
 * <p>
 * 使用 ReentrantLock 可以完成同样的功能
 * 需要注意的是，必须要手动释放锁，使用 syn 锁定的话如果遇到异常，JVM 会自动释放锁，但是 lock 必须手动释放锁，因此经常在 finally 中进行锁的释放。
 * <p>
 * 使用 ReentrantLock 可以进行“尝试锁定” tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待。
 * <p>
 * 使用 ReentrantLock 还可以调用 lockInterruptibly 方法，可以对线程 interrupt 方法做出响应，在一个线程等待锁的过程中，可以被打断。
 * <p>
 * ReentrantLock 还可以指定为公平锁
 */
public class ReentrantLock5 extends Thread {

    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 rL = new ReentrantLock5();

        Thread t1 = new Thread(rL);
        Thread t2 = new Thread(rL);

        t1.start();
        t2.start();
    }
}

















