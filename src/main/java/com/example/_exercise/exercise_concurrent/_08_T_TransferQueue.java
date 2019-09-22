package com.example._exercise.exercise_concurrent;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue：是一个无界的阻塞队列，底层由链表实现。
 * 虽然和 LinkedBlockingQueue 一样也是链表实现的，但并发控制的实现上却很不一样，
 * 和SynchronousQueue类似，采用了大量的 CAS 操作，没有使用锁，由于是无界的，
 * 所以不会 put 生产线程不会阻塞，只会在 take 时阻塞消费线程，消费线程挂起时同样使用 LockSupport.park 方法。
 *
 * LinkedTransferQueue 相比于以上的队列还提供了一些额外的功能，它实现了TransferQueue接口，
 * 有两个关键方法 transfer(E e) 和 tryTransfer(E e) 方法，
 *          transfer 在没有消费时会阻塞；
 *          tryTransfer 在没有消费时不会插入到队列中，也不会等待，直接返回 false；
 */
public class _08_T_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<Object> lTQ = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(lTQ.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        lTQ.transfer("aaa");

        /*new Thread(() -> {
            try {
                System.out.println(lTQ.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

}
