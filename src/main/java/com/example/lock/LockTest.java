package com.example.lock;


import java.util.concurrent.locks.ReentrantLock;


 /**
  * @ProjectName:
  * @Package:        com.example.lock
  * @ClassName:      LockTest
  * @Description:
  *                  使用 lock 锁：解决线程安全问题（JDK 5.0新增）
  *
  *                  面试题：synchronized 与 lock 的异同？
  *                  相同点：二者都是可以解决线程安全问题
  *                  不同点：synchronized 机制在执行完相应的同步代码以后，自动的释放同步监视器；
  *                         Lock 需要手动的启动同步（lock()）,同时结束同步也需要手动的实现（unlock()）。
  *
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/6 18:02
  * @Version:        1.0.0
  */
public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable {

    private int ticket = 100;

    //实例化 ReentrantLock
    //如果是有参构造：new ReentrantLock(true) --> 表示为公平调度，结果为 t1、t2、t3 三个线程轮流调度
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {

            try {

                //调用锁定方法 lock()
                lock.lock();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "：售票，票号为 " + ticket);
                    ticket--;
                } else {
                    break;
                }

            } finally {

                //调用解锁方法 unlock()
                lock.unlock();
            }
        }
    }
}

