package com.example.synchronized_method;


 /**
  * @ProjectName:
  * @Package:        com.example.tick_test.synchronized_method
  * @ClassName:      WindowThread
  * @Description:
  *                  需求：创建三个窗口卖票，总票数为100张，使用继承 Thread 类的方式。
  *
  *                  使用同步方法：解决线程安全问题
  *
  *                  关于同步方法的总结：
  *                  1)同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
  *                  2)非静态的同步方法，同步监视器是 this；静态的同步方法，同步监视器是当前类的本身。
  *
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/6 11:17
  * @Version:        1.0.0
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

class WindowT extends Thread{

    private static int ticket = 100;


    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            show();
        }
    }

    private static synchronized void show() {

    /**
     *下面这种写法是错误的，因为这时候的同步监听器为 this 表示 t1、t2、t3 三个对象。
     */
    //private synchronized void show() {

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