package com.example.communication;


 /**
  * @ProjectName:
  * @Package:        com.example.communication
  * @ClassName:      WaitNotify
  * @Description:
  *                  线程通信的例子：
  *                  需求：使用两个线程打印 1-100。线程1、线程2交替打印。
  *
  *                  涉及到三个方法：
  *                  wait()：一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器。
  *                  notify()：一旦执行此方法，就会唤醒被 wait() 的一个线程。如果有多个线程被 wait()，就唤醒优先级高的那个。
  *                  notifyAll()：一旦执行此方法，就会唤醒所以被 wait() 的线程。
  *
  *                  说明：
  *                  1) wait()、notify()、notifyAll() 这三个方法必须使用在同步代码块或同步方法中。
  *                  2) wait()、notify()、notifyAll() 这三个方法的调用者必须是同步代码块或同步方法中的同步监视器，
  *                                                  否则会出现 IllegalMonitorStateException 异常。
  *                  3) wait()、notify()、notifyAll() 这三个方法是定义在 java.lang.Object 类中的。
  *
  *                  面试题：sleep() 和 wait() 的异同?
  *                  1) 两个方法声明的位置不同：Thread 类中声明 sleep()，Object 类中声明 wait()
  *                  2) 调用的要求不同：sleep() 可以在任何需要的场景下调用。wait() 必须使用在同步代码块或同步方法中调用。
  *                  3) 关于同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep() 不会释放锁，wait() 会释放锁。
  *                  4) sleep() 方法需要抛异常，wait()方法不需要。
  *
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/6 23:34
  * @Version:        1.0.0
  */
public class WaitNotify {
    public static void main(String[] args) {
        Number n = new Number();

        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

class Number implements Runnable {

    private int number = 1;

    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {

                //线程唤醒
                obj.notify();

                if (number <= 100) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //线程等待
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}