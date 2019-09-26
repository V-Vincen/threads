package com.example._exercise.exercise_threadpool;

/**
 * Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池
 *
 * Executors.newCachedTreadPool()：创建一个可缓存的线程池，调用 execute 将重用以前构造的线程（如果线程可用）。如果没有可用的线程，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
 * Executors.newFixedThread(n)：创建一个可重用固定线程的线程池。
 * Executors.newSingleThreadExecutor()：创建一个只有一个线程的线程池。
 * Executors.newScheduledThreadPool(n)：创建一个线程池，它可以安排在给定延迟后运行命令或者定期地执行。
 */
public class _04_T_Executors {
}
