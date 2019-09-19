package com.example._exercise.exercise_threadlocal;


import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 线程局部变量
 * <p>
 * ThreadLocal 是使用空间换时间，synchronized 是使用时间换空间；
 * 比如在 hibernate 中 session 就存在于 ThreadLocal 中，避免 synchronized 的使用。
 */
public class ThreadLocal2 {

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get().name);
        }, "t2").start();
    }

    static class Person {
        String name = "zhangsan";
    }
}


