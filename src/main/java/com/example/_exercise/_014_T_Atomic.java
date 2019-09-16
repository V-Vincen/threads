package com.example._exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


//解决同样的问题的更高效率的方法，使用 AtomXXX 类，
//AtomXXX 类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的。
public class _014_T_Atomic {

    AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();//count++;
            System.out.println(Thread.currentThread().getName() + " count:" + count);
        }
    }

    public static void main(String[] args) {
        _014_T_Atomic tAtomic = new _014_T_Atomic();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(tAtomic::m, "thread-" + i));
        }

        threads.forEach((thread -> thread.start()));

        threads.forEach((thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        System.out.println(tAtomic.count);
    }
}
