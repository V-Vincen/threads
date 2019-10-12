package com.example._exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 并不能保证多个线程共同修改 running 变量时所带来的不一致问题，也就是 volatile 不能替代 synchronized。
 * 也就是说 volatile 只能保证线程数据之间的可见性，但并不能保证线程之间的原子性。
 */
public class _012_T_VolatileAndSyn {
    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) count++;
    }

    public static void main(String[] args) {
        _012_T_VolatileAndSyn tVolatileAndSyn = new _012_T_VolatileAndSyn();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(tVolatileAndSyn::m, "thread-" + i));
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

        System.out.println(tVolatileAndSyn.count);
    }
}















