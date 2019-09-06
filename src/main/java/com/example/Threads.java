package com.example;

public class Threads {

    public static void main(String[] args) {

        Thread threadRunnable = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        });
        threadRunnable.start();


        //创建 Thread 匿名子类的方式
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        };
        thread.start();

    }
}
