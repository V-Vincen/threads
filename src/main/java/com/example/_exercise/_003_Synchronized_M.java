package com.example._exercise;

//synchronized 关键词：对某个对象加锁
public class _003_Synchronized_M {

    private int count = 10;

    public synchronized void m() {//等同于在方法的代码执行时，要 synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}
