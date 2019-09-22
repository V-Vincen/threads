package com.example._exercise.exercise_concurrent;

import com.example.Threads;
import com.sun.org.glassfish.gmbal.Description;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器：copy on write
 * 多线程环境下，写时效率低，读是效率高
 * 适合用在写少读多的环境
 */
public class _02_T_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();

        Random random = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) list.add("a" + random.nextInt(10000));
                }
            };
            threads[i] = new Thread(runnable);
        }

        runAndComputeTime(threads);

        System.out.println(list.size());
    }

    private static void runAndComputeTime(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.asList(threads).forEach(thread -> thread.start());
        Arrays.asList(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
