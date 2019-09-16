package com.example._exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比上一个程序，可以用 synchronized 解决，synchronized 可以保证可见性和原子性，volatile 只能保证可见性。
 */
public class _013_T_VolatileAndSyn2 {
    int count = 0;

    synchronized void m() {
        for (int i = 0; i < 10000; i++) count++;
    }

    public static void main(String[] args) {
        _013_T_VolatileAndSyn2 tVolatileAndSyn2 = new _013_T_VolatileAndSyn2();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(tVolatileAndSyn2::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        //原来的写法
//        threads.forEach(new Consumer<Thread>() {
//            @Override
//            public void accept(Thread o) {
//                o.start();
//            }
//        });

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //原来的写法
//        threads.forEach(new Consumer<Thread>() {
//            @Override
//            public void accept(Thread thread) {
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        System.out.println(tVolatileAndSyn2.count);

    }
}















