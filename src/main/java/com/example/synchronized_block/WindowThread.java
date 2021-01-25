package com.example.synchronized_block;


/**
 * @ProjectName:
 * @Package: com.example.ticktest
 * @ClassName: WindowThread
 * @Description: 需求：创建三个窗口卖票，总票数为100张，使用继承 Thread 类的方式。
 * <p>
 * 使用同步代码块：解决继承 Thread 类的方式的线程安全问题
 * <p>
 * 说明：在继承 Thread 类创建多线程的方式中，慎用 this 充当同步监视器，考虑使用当前类充当同步监视器。
 * @Author: Mr.Vincent
 * @CreateDate: 2019/9/6 1:06
 * @Version: 1.0.0
 */
public class WindowThread {
    public static void main(String[] args) {
        WindowT t1 = new WindowT();
        WindowT t2 = new WindowT();
        WindowT t3 = new WindowT();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class WindowT extends Thread {
    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {

                /**
                 * 或者使用 WindowT.class：这里的 WindowT.class 也是个对象，相当于 Class clazz = WindowT.class，类是唯一的，在 JVM 中只会加载一次；
                 * 这里就不能用 this 了，因为这时候的 this 表示 t1、t2、t3 三个对象，相当于是三把锁了，并不是同一把锁。
                 */
                //synchronized (WindowT.class){
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}