package com.example._exercise.exercise_container;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：
 * 实现一个容器，提供两个方法，add，size。
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束。
 * <p>
 * 给 lists 添加 volatile 之后，t2 能够接到通知，但是，t2线程的死循环很浪费 cpu，如果不用死循环，改怎么做呢？
 * <p>
 * 这里使用 wait 和 notify 做到，wait 会释放锁，而 notify 不会释放锁，
 * 需要注意的是，运用这种方法，必须要保证 t2 先执行，也就是首先让 t2 监听才可以。
 * <p>
 * 阅读下面的程序，并分析输出结果：
 * 可以读到输出结果并不是 size=5 时 t2 退出，而是 t1 结束时 t2 才接收到通知而退出。
 * 想想这是为什么？
 * <p>
 * notiy 之后，t1 必须释放锁，t2 退出后，也必须 notify，通知 t1 继续执行，
 * 整个通信过程比较繁琐。
 * <p>
 * 使用 Latch（门闩）替代 wait notify 来进行通知：
 * 好处是通信方式简单，同时也可以指定等待时间，使用 await 和 countdown 方法替代 wait 和 notify；
 * CountDownLatch 不涉及锁定，当 count 的值为零时当前线程继续运行，当不涉及同步，只是涉及线程通信的时候，用 synchronized + wait/notify 就显得太重了，
 * 这时应该考虑 countdownlatch/cyclicbarrier/semapjore
 */
public class MyContainer5 {

    //添加 volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer5 c = new MyContainer5();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 启动");
            if (c.size() != 5) {
                try {
                    latch.await();

                    //也可以指定等待时间
                    //latch.await(5000,TimeUnit.MICROSECONDS);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());

                System.out.println("add " + i);

                if (c.size() == 5) {
                    //打开门闩，让 t2 的以执行
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

    }

}
