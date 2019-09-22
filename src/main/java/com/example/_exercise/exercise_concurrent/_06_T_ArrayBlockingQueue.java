package com.example._exercise.exercise_concurrent;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue：是一个底层用数组（准确的说是一个循环数组（可以类比一个圆环），所有的下标在到达最大长度时自动从0继续开始。）实现的有界阻塞队列，有界是指他的容量大小是固定的，不能扩充容量，在初始化时就必须确定队列大小。
 *                    它通过可重入的独占锁 ReentrantLock 来控制并发，Condition 来实现阻塞。
 * 其主要的方法为：
 * 1.取数据：
 *      take()：首选，当队列为空时阻塞。
 *      poll()：弹出队顶元素，队列为空时，返回空。
 *      peek()：和 poll 类似，返回队顶元素，但顶元素不弹出。队列为空时返回 null。
 *      remove(Object o)：移除某个元素，队列为空时抛出异常。成功移除返回 true。
 * 2.添加数据：
 *      put()：首选，队满时阻塞。
 *      add()：插入元素到队尾，插入成功返回 true，没有可用空间抛出异常 IllegalStateException。
 *      offer()：队满时返回 false。（插入元素到队尾，插入成功返回 true，否则返回 false。）
 */
public class _06_T_ArrayBlockingQueue {

    static BlockingQueue<String> aBQ = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            aBQ.put("a" + i);
        }

        //满了时，会阻塞
        aBQ.put("aaa");

        //add(E e)：插入元素到队尾，插入成功返回 true，没有可用空间抛出异常 IllegalStateException。
//        aBQ.add("aaa");

        //offer(E e)：插入元素到队尾，插入成功返回 true，否则返回 false。
//        boolean offer = aBQ.offer("aaa");
//        aBQ.offer("aaa",1, TimeUnit.SECONDS);

        System.out.println(aBQ);
    }
}
