package com.example;


 /**
  * @ProjectName:
  * @Package:        com.example
  * @ClassName:      RunnableDemo
  * @Description:
  *                  创建线程的方式一：实现 Runnable 接口
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/8 18:14
  * @Version:        1.0.0
  */
public class RunnableDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}

class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
