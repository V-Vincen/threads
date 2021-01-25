package com.example._exercise;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键词，是一个变量在多线程间可见，
 * A B 线程都用到一个变量，java 默认是线程中保留一份 copy，这样如果 B 线程改了该变量，则 A 线程未必知道，
 * 使用 volatile 关键字，会让所有线程都会读到变量的修改值。
 * <p>
 * 在下面的代码中，running 是存在于堆内存的 t 对象中，
 * 当线程 t1 开始运行的时候，会把 running 值从内存中读到 t1 线程的工作区，在运行过程中直接使用这个 copy，
 * 并不会每次都去读取堆内存，这样，当主线程修改 running 的值之后，t1 线程感知不到，所以不会停止运行。
 * <p>
 * 使用 volatile，将会强制所有线程都去堆内存中读取 running 的值。
 * volatile 并不能保证多个线程共同修改 running 变量时所带来的不一致问题，也就是 volatile 不能替代 synchronized。
 * 也就是说 volatile 只能保证线程数据之间的可见性，但并不能保证线程之间的原子性。
 */
public class _011_T_Volatile {

    //用 static 修饰也可以达到数据共享的效果，但是这时候需要下面的 while 方法内的程序，主动去调用 running，否则正在运行的线程不会主动去更新修改后的 running 全局类变量。
    volatile boolean running = true;//对比一下有无 volatile 的情况下，整个程序运行结果的区别

    void m() {
        System.out.println("m start");
        while (running) {
//            System.out.println(running);
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        _011_T_Volatile tVolatile = new _011_T_Volatile();

        new Thread(tVolatile::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tVolatile.running = false;
    }
}















