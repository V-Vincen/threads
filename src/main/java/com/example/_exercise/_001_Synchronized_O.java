package com.example._exercise;

//synchronized 关键词：对某个对象加锁
public class _001_Synchronized_O {

    private int count = 10;
    private Object o = new Object();

    public void m() {
        synchronized (o) {//任何线程操作要执行下面的代码，必须拿到 o 的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
