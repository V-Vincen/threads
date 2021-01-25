package com.example._exercise;


import java.util.concurrent.TimeUnit;

//synchronized 优化，同步代码块中是语句越少越好，m1 和 m2 的比较
public class _015_T_SynOptimize {

    int count = 0;

    synchronized void m1() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //业务逻辑中只有下面这句话需要 sync，这时不应该给整个方法上锁
        count++;

        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //业务逻辑中只有下面这句话需要 sync，这时不应该给整个方法上锁
        //采用细颗粒的锁，可以使线程争用时间变短，从而提高效率
        synchronized (this) {
            count++;
        }

        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
