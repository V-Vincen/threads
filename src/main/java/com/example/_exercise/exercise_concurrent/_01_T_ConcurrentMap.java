package com.example._exercise.exercise_concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class _01_T_ConcurrentMap {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();
//        Map<String,String> map = new ConcurrentSkipListMap<>();//高并发并且排序

//        Map<String, String> map = new Hashtable<>();
//        Map<String,String> map = new HashMap<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);

        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                latch.countDown();
            });
        }

        List<Thread> threads = Arrays.asList(ths);
        threads.forEach(thread -> thread.start());

        try {
            //main线程阻塞，直到计数器变为0，才会继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
