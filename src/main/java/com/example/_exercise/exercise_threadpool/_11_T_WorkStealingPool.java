package com.example._exercise.exercise_threadpool;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  newWorkStealingPool（工作窃取）：适合使用在很耗时的操作，但是 newWorkStealingPool 不是 ThreadPoolExecutor 的扩展。
 * 它是新的线程池类 ForkJoinPool 的扩展（也就是说它的底层实现是 ForkJoinPool），但是都是在统一的一个 Executors 类中实现。
 * 由于能够合理的使用 CPU 进行对任务操作（并行操作），所以适合使用在很耗时的任务中。
 */
public class _11_T_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        //daemon：守护线程
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        //由于生产的精灵线程（守护线程、后天线程），主线程不阻塞的话，看不到
        System.in.read();
    }

    static class R implements Runnable {
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
}
