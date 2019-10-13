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
 */
public class ReentrantLock4 {
    /**
     * 线程1现在持有 lock 这把锁，在线程1启动后，其无限睡死了；
     * 这时线程2启动了，同时线程2，也去申请 lock 这把锁，但是由于线程1，在无限制的持有 lock 这把锁，所以线程2拿不到 lock 这把锁，只能进行等待；
     * 如果想要打断正在等待中的线程2，这时我们就要用 t2.interrupt() 方法来打断线程2，
     * 而 lock.lockInterruptibly() 方法可以对 t2.interrupt() 方法做出响应，并且捕获 InterruptedException 异常。
     */
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        boolean locked = false;

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();//synchronized(this)
                System.out.println("t1 start");

                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);//睡死了

                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted! t1");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
//                lock.lock();//synchronized(this)
                lock.lockInterruptibly();//可以对 interrupt() 方法做出响应
                System.out.println("t2 start");

                TimeUnit.SECONDS.sleep(5);

                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted! t2");
            } finally {
                if (locked) lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();//打断线程2的等待
    }
}

















