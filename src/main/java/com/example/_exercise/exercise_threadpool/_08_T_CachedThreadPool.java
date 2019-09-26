package com.example._exercise.exercise_threadpool;

import java.util.concurrent.*;

/**
 * Executor.newCachedTreadPool()：创建一个可缓存的线程池，调用 execute 将重用以前构造的线程（如果线程可用）。
 * 如果没有可用的线程，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
 */
public class _08_T_CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        //自定义 CachedPool 的终止时间为 30L
//        ExecutorService service = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        TimeUnit.SECONDS.sleep(70);

        System.out.println(service);
    }
}
