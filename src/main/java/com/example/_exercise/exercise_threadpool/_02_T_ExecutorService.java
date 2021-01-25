package com.example._exercise.exercise_threadpool;


/**
 * ExecutorService：真正的线程池接口，其继承了 Executor。
 * 提供的方法：
 * void execute(Runnable command)：执行任务/命令，没有返回值，一般用来执行 Runnable
 * <T> Future<T> submit(Callable<T> task)：执行任务，有返回值，一般有来执行 Callable
 * void shutdown()：关闭连接池
 * ...
 */
public class _02_T_ExecutorService {
}
