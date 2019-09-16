package com.example._exercise.exercise_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock 用于替代 synchronized，本例中由于 m1 锁定 this，只有 m1 执行完毕的时候，m2 才能执行，
 * 这里是复习 synchronized 最原始的语义。
 * <p>
 * 使用 reentrantlock 可以完成同样的功能
 * 需要注意的是，必须要手动释放锁，使用 syn 锁定的话如果遇到异常，JVM 会自动释放锁，但是 lock 必须手动释放锁，因此经常在 finally 中进行锁的释放。
 */
public class ReentrantLock3 {
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

    /**
     * 使用 tryLock 进行尝试锁定，然后可以根据 tryLock 的返回值来判定是否锁定，再根据业务需求进行编码；
     * 也可以指定 tryLock 的时间，由于 tryLock(time) 抛出异常，所以要注意 unlock 的处理，必须放在 finally 中。
     */
    void m2() {
        boolean locked = lock.tryLock();
        System.out.println("m2..." + locked);
        if (locked) lock.unlock();

//        boolean locked = false;
//
//        try {
//            lock.tryLock(5, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            if (locked) lock.unlock();
//        }

    }

    public static void main(String[] args) {
        ReentrantLock3 rL = new ReentrantLock3();

        new Thread(rL::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(rL::m2).start();
    }
}
