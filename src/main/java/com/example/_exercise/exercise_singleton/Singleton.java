package com.example._exercise.exercise_singleton;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 线程安全的单例模式：
 * （更多相关可阅读：https://wvincen.gitee.io/2019/08/05/Java-%E6%9D%82%E8%AE%B0-GoF-%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F/）
 *
 * 更好的采用下面的方式：既不用加锁，也能实现懒加载
 *
 */
public class Singleton {
    private Singleton(){
        System.out.println("single");
    }

    private static class Inner{
        private static Singleton s = new Singleton();
    }

    private static Singleton getInstance(){
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                Singleton.getInstance();
            });
        }

        List<Thread> threads = Arrays.asList(ths);

        //原来的写法
//        threads.forEach(new Consumer<Thread>() {
//            @Override
//            public void accept(Thread thread) {
//                thread.start();
//            }
//        });

        //lambda 表达式
        threads.forEach(thread -> thread.start());
    }
}
