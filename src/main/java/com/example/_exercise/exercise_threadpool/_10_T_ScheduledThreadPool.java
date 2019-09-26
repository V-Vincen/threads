package com.example._exercise.exercise_threadpool;


import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool(n)：创建一个线程池，它可以安排在给定延迟后运行命令或者定期地执行。
 */
public class _10_T_ScheduledThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        /**
         * scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit)：以固定的频率去执行任务。
         * 4个参数：
         *      Runnable command：实现 Runnable 接口
         *      long initialDelay：初始化线程启动延迟的时间
         *      long period：每隔多长时间执行任务（间隔的频率）
         *      TimeUnit unit：第三个参数的时间单位
         */
        service.scheduleAtFixedRate(() -> {
            try {
//                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);


        TimeUnit.SECONDS.sleep(20);
        service.shutdown();
    }
}
