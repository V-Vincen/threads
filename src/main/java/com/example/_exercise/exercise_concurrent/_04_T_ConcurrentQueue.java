package com.example._exercise.exercise_concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class _04_T_ConcurrentQueue {
    public static void main(String[] args) {
        //ConcurrentLinkedQueue 线程安全的无界队列，底层采用单链表，支持 FIFO。
        Queue<String> lQ = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            //offer 添加，类似于 add
            lQ.offer("a" + i);
        }

        System.out.println(lQ);

        System.out.println(lQ.size());

        //poll 从头部获取元素，获取后会删除元素
        System.out.println(lQ.poll());
        System.out.println(lQ);
        System.out.println(lQ.size());

        //peek 从头部获取元素，获取后不会删除元素
        System.out.println(lQ.peek());
        System.out.println(lQ);
        System.out.println(lQ.size());

        //ConcurrentLinkedDeque：线程安全的双端无界阻塞队列，底层采用双向链表，支持 FIFO 和 FILO。
    }
}
