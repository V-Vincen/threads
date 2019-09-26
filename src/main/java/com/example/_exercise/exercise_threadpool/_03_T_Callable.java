package com.example._exercise.exercise_threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable 接口和 Runnable 接口相似，区别就是 Callable 需要实现 call 方法，而 Runnable 需要实现 run 方法；
 * 并且，call 方法还可以返回任何对象，无论是什么对象，JVM 都会当作 Object 来处理。但是如果使用了泛型，我们就不用每次都对 Object 进行转换了。
 */
public class _03_T_Callable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1000;
    }

    public static void main(String[] args) {
        _03_T_Callable tCallable = new _03_T_Callable();

        /**
         * FutureTask
         * 1) 作为 Funture 的唯一实现类，可以得到 Callable 的返回值
         * 2) 同时实现了 Runnable 接口，可以作为 Runnable 被线程执行
         *
         */
        FutureTask<Integer> futureTask = new FutureTask<>(tCallable);

        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
