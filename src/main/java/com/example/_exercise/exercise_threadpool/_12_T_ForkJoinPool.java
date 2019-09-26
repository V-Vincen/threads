package com.example._exercise.exercise_threadpool;


import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 *
 */
public class _12_T_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println(Arrays.stream(nums).sum());
    }

    //RecursiveAction 继承 ForkJoinTask，其无返回值。
    static class AddAction extends RecursiveAction {

        int start, end;

        public AddAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            } else {
                int middle = start + (end - start) / 2;
                AddAction subAct1 = new AddAction(start, middle);
                AddAction subAct2 = new AddAction(middle, end);
                subAct1.fork();
                subAct2.fork();
            }
        }
    }

    //RecursiveTask<V> 继承 ForkJoinTask，其有返回值。
    static class AddTask extends RecursiveTask<Long> {
        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from:" + start + " to:" + end + " = " + sum);
                return sum;
            } else {
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();

                return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ForkJoinPool fjp = new ForkJoinPool();

        /**
         * RecursiveAction 的执行方法
         */
        AddAction addAction = new AddAction(0, nums.length);
        fjp.execute(addAction);
        System.in.read();


        /**
         * RecursiveTask 的执行方法
         */
        AddTask addTask = new AddTask(0, nums.length);
//        fjp.execute(addTask);
//        Long result = addTask.join();

        Future<Long> submit = fjp.submit(addTask);
        Long result = submit.get();

        System.out.println(result);
    }
}
