package com.example;

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setName("线程一");
        thread.start();
        System.out.println("i am main Thread");

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            if(i % 4 == 0){
                yield();
            }
        }
    }
}