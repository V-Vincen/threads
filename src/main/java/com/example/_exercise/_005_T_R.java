package com.example._exercise;

public class _005_T_R implements Runnable {

    private int count = 10;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        _005_T_R tR = new _005_T_R();
        for (int i = 0; i < 5; i++) {
            new Thread(tR, "Thread" + i).start();
        }
    }
}
