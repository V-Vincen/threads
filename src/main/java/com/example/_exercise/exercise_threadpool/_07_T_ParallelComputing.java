package com.example._exercise.exercise_threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * 获取 1 到 200000 之间的所有质数，比较传统写法（不用线程池）和使用线程池之间的效率。
 */
public class _07_T_ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> result = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        final int cpuCoreNum = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> future1 = service.submit(t1);
        Future<List<Integer>> future2 = service.submit(t2);
        Future<List<Integer>> future3 = service.submit(t3);
        Future<List<Integer>> future4 = service.submit(t4);

        start = System.currentTimeMillis();
        future1.get();
        future2.get();
        future3.get();
        future4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);

        service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> prime = getPrime(startPos, endPos);
            return prime;
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (isPrime(i)) results.add(i);
        }
        return results;
    }
}
