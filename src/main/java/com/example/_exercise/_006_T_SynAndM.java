package com.example._exercise;

//同步和非同步方法是否可以同时调用
public class _006_T_SynAndM {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end...");
    }

    public void m2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2");
    }

    public static void main(String[] args) {
        _006_T_SynAndM t = new _006_T_SynAndM();

        new Thread(() -> t.m1(), "t1").start();
        new Thread(() -> t.m2(), "t2").start();

//        或者方法引用
//        new Thread(t::m1,"t1").start();
//        new Thread(t::m1,"t2").start();

//        或者原来的写法
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.m1();
//            }
//        }, "t1").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.m2();
//            }
//        }, "t2").start();
    }
}
