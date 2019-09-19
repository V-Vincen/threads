package com.example._exercise.exercise_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 用于替代 Synchronized，本例中由于 m1 锁定 this，只有 m1 执行完毕的时候，m2 才能执行，
 * 这里是复习 Synchronized 最原始的语义。
 */
public class ReentrantLock1 {

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        ReentrantLock1 rL = new ReentrantLock1();

        new Thread(rL::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(rL::m2).start();
    }

}
