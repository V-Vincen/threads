package com.example._exercise.exercise_concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * DelayQueue：是一个支持延时获取元素的无界阻塞队列，队列使用 PriorityQueue 来实现。
 *            队列中的元素必须实现 Delayed 接口，在创建元素时可以指定多久才能从队列中获取当前元素，只有在延迟期满时才能从队列中提取元素。
 * 我们可以将 DelayQueue 运用在以下应用场景：
 *          缓存系统的设计：可以用 DelayQueue 保存缓存元素的有效期，使用一个线程循环查询 DelayQueue，一旦能从 DelayQueue 中获取元素时，表示缓存有效期到了。
 *          定时任务调度：使用 DelayQueue 保存当天将会执行的任务和执行时间，一旦从 DelayQueue 中获取到任务就开始执行，从比如 TimerQueue 就是使用 DelayQueue 实现的。
 */
public class _07_T_DelayQueue {

    static BlockingQueue<MyTask> dQ = new DelayQueue<>();

    static Random r = new Random();

    private static class MyTask implements Delayed {
        long runningTime;

        public MyTask(long runningTime) {
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask(now + 1000);
        MyTask t2 = new MyTask(now + 2000);
        MyTask t3 = new MyTask(now + 1500);
        MyTask t4 = new MyTask(now + 2500);
        MyTask t5 = new MyTask(now + 500);

        dQ.put(t1);
        dQ.put(t2);
        dQ.put(t3);
        dQ.put(t4);
        dQ.put(t5);

        System.out.println(dQ);

        for (int i = 0; i < 5; i++) {
            System.out.println(dQ.take());
        }
    }
}
