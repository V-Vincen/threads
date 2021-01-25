package com.example.synchronized_method;


/**
 * @ProjectName:
 * @Package: com.example.tick_test.synchronized_method
 * @ClassName: WindowRunnable
 * @Description: 需求：创建三个窗口卖票，总票数为100张，使用实现 Runnable 接口的方式。
 * <p>
 * 使用同步方法：解决线程安全问题
 * 同步方法：如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。
 * <p>
 * 关于同步方法的总结：
 * 1)同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 * 2)非静态的同步方法，同步监视器是 this；静态的同步方法，同步监视器是当前类的本身。
 * @Author: Mr.Vincent
 * @CreateDate: 2019/9/6 10:32
 * @Version: 1.0.0
 */
public class WindowRunnable {
    public static void main(String[] args) {
        WindowR windowR = new WindowR();

        Thread t1 = new Thread(windowR);
        Thread t2 = new Thread(windowR);
        Thread t3 = new Thread(windowR);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class WindowR implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            show();
        }
    }

    //同步监视器：this 表示为当前类的对象
    public synchronized void show() {
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
            ticket--;
        }
    }

}