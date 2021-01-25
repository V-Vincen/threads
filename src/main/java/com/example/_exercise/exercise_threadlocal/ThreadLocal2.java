package com.example._exercise.exercise_threadlocal;


import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 线程局部变量
 * <p>
 * ThreadLocal 是使用空间换时间，synchronized 是使用时间换空间；
 * 比如在 hibernate 中 session 就存在于 ThreadLocal 中，避免 synchronized 的使用。
 */
public class ThreadLocal2 {
    /**
     * ThreadLocal：线程局部变量
     * 下面程序运行结果为：
     * t2:zhangsan
     * t1:null
     * 因为线程t2中 tL.set(new Person())，而线程t1并没有；
     * 所以 ThreadLocal 是线程局部变量，线程t2需要用就自己 set，线程t1是共享不了的。
     */
    static ThreadLocal<Person> tL = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + tL.get());
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tL.set(new Person());
            System.out.println(Thread.currentThread().getName() + ":" + tL.get().name);
        }, "t2").start();
    }

    static class Person {
        String name = "zhangsan";
    }
}


