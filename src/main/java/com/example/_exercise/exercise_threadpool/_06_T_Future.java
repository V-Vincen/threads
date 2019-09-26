package com.example._exercise.exercise_threadpool;

import java.util.concurrent.*;


/**
 * 实现 Callable 接口，获取 call 方法返回值的两种方法。
 * 1) 调用 FutureTask 实例对象的 get() 方法
 * 2) 创建线程池，调用 ExecutorService 实例对象的 submit() 方法，调用其返回对象 Future 的 get() 方法
 */
public class _06_T_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(futureTask).start();

        System.out.println(futureTask.get());//阻塞


//======================================================================================================================
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> submit = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

        System.out.println(submit.get());

        //判断 ExecutorService 的状态
        System.out.println(submit.isDone());
    }
}
