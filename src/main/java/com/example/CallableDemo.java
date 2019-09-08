package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


 /**
  * @ProjectName:
  * @Package:        com.example
  * @ClassName:      CallableDemo
  * @Description:
  *                  创建线程的方式三：实现 Callable 接口 --> JDK 5.0 新增
  *
  *                  如何理解实现 Callable 接口的方式创建多线程比实现 Runnable 接口的方式创建多线程，更强大
  *                  1) call() 方法有返回值
  *                  2) call() 可以抛出异常，被外面的操作捕获，获取异常信息
  *                  3) Callable 接口是支持泛型的
  *
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/8 19:03
  * @Version:        1.0.0
  */
public class CallableDemo {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();

        /**
         * FutureTask
         * 1) 作为 Funture 的唯一实现类，可以得到 Callable 的返回值
         * 2) 同时实现了 Runnable 接口，可以作为 Runnable 被线程执行
         *
         */
        FutureTask<Integer> futureTask = new FutureTask<Integer>(myCallable);

        Thread thread = new Thread(futureTask);
        thread.start();

        try {

            //get() 返回值即为 FutrueTask 构造参数 Callable 实现类重写的 call() 的返回值
            Integer sum = futureTask.get();
            System.out.println("总和为：" + sum);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}