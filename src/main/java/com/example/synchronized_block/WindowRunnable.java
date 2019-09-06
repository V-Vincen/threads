package com.example.synchronized_block;


 /**
  * @ProjectName:
  * @Package:        com.example.ticktest
  * @ClassName:      WindowTest
  * @Description:
  *                  需求：创建三个窗口卖票，总票数为100张，使用实现 Runnable 接口的方式。
  *
  *                  线程安全问题：卖票过程中，出现了重票、错票。
  *                  问题的原因：当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票。
  *                  如何解决：当一个线程a 在操作 ticket 的时候，其他线程不能参与进来。直到线程a 操作完 ticket 时，其他线程才可以操作 ticket 。
  *                          这种情况即使线程a 出现了阻塞，也不能改变。
  *
  *                  在 java 中，我们通过同步机制，来解决线程的安全问题。
  *
  *                  使用同步代码块：解决线程安全问题
  *                  synchronized（同步监视器）{
  *                      //需要被同步的代码
  *                  }
  *                  说明：1.操作共享数据的代码，即为需要被同步的代码。
  *                       2.共享数据：多个线程共同操作的变量。比如：本案例中 ticket 就是共享数据。
  *                       3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。（注意：多线程必须要共用同一把锁。）
  *                  补充：在实现 Runnable 接口创建多线程的方式中，我们可以考虑用 this 充当同步监视器。
  *
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/6 0:16
  * @Version:        1.0.0
  */
public class WindowRunnable {
    public static void main(String[] args) {
        WindowR window = new WindowR();

        Thread t1 = new Thread(window);
        Thread t2 = new Thread(window);
        Thread t3 = new Thread(window);

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
    Object obj = new Object();

    public void run() {
        while (true) {
            synchronized (obj) {

                /**
                 * 或者使用 this：表示当前类的对象，也就是 WindowR 的对象
                 */
//                synchronized (this) {

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