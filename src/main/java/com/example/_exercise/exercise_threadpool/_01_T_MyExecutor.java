package com.example._exercise.exercise_threadpool;

import java.util.concurrent.Executor;


/**
 * Executor：线程池中的顶层接口
 */
public class _01_T_MyExecutor implements Executor {
    public static void main(String[] args) {
        new _01_T_MyExecutor().execute(() -> System.out.println("hello executor"));
    }

    @Override
    public void execute(Runnable command) {
        command.run();
//        new Thread(command).start();
    }
}
