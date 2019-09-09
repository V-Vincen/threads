package com.example;


import java.util.concurrent.*;

/**
  * @ProjectName:
  * @Package:        com.example
  * @ClassName:      ThreadPool
  * @Description:
  *                   创建线程的方式四：使用线程池 --> JDK 5.0 新增
  *
  *
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/9 10:38
  * @Version:        1.0.0
  */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //创建线程池的初始化参数
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
//        threadPoolExecutor.setCorePoolSize();
//        threadPoolExecutor.setMaximumPoolSize();
//        threadPoolExecutor.setKeepAliveTime();


        //适用于 Runnable
        executorService.execute(new MyThreadPool());

        //适用于 Callable
        Future submit = executorService.submit(new MyThreadPool2());

        try {
            int sum = (int) submit.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class MyThreadPool implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}

class MyThreadPool2 implements Callable {
    int sum = 0;

    @Override
    public Object call() {
        for (int i = 0; i < 101; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                sum += i;
            }
        }
        return sum;
    }
}
