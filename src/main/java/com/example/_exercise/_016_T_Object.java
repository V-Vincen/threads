package com.example._exercise;


import java.util.concurrent.TimeUnit;

//锁定对象 o，如果 o 的属性发生改变，不影响锁的使用，但是如果 o 变成另一个对象，
//则锁定的对象发生改变，应该避免将锁定对象的引用变成另外的对象。
public class _016_T_Object {

    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        _016_T_Object tObject = new _016_T_Object();

        //启动第一个线程
        new Thread(tObject::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建第二个线程
        Thread t2 = new Thread(tObject::m, "t2");

        tObject.o = new Object();//锁对象发生改变，所以 t2 线程得以执行，如果注释掉这就话，线程2将永远得不到执行机会

        t2.start();
    }
}








