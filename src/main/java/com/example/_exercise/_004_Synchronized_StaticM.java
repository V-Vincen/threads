package com.example._exercise;

//synchronized 关键词：对某个对象加锁
public class _004_Synchronized_StaticM {

    private static int count = 10;

    public synchronized static void m() {//这里等同于 synchronized(_004_Synchronized_StaticM.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm(){
        synchronized (_004_Synchronized_StaticM.class){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
