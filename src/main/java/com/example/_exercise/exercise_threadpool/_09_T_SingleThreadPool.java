package com.example._exercise.exercise_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Executors.newSingleThreadExecutor()：创建一个只有一个线程的线程池。
 */
public class _09_T_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> {
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

        service.shutdown();
    }
}
