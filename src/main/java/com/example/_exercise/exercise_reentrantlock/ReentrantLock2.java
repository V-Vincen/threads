package com.example._exercise.exercise_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 用于替代 Synchronized，本例中由于 m1 锁定 this，只有 m1 执行完毕的时候，m2 才能执行，
 * 这里是复习 Synchronized 最原始的语义。
 * <p>
 * 使用 ReentrantLock 可以完成同样的功能
 * 需要注意的是，必须要手动释放锁，使用 syn 锁定的话如果遇到异常，JVM 会自动释放锁，但是 lock 必须手动释放锁，因此经常在 finally 中进行锁的释放。
 */
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();//synchronized(this)
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    void m2() {
        lock.lock();
        System.out.println("m2...");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock2 rL = new ReentrantLock2();

        new Thread(rL::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(rL::m2).start();
    }

}
