package com.example._exercise.exercise_ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有 N 张火车票，每张票都有一个编号，同时又10个窗口对外售票，请写一个模拟程序。
 * <p>
 * 分析下面的程序可能会产生那些问题？
 * 重复销售？超量销售？
 *
 * 虽然说 Vector 是线程安全的，但是 while 中的操作不是原子操作，最后一张片极有可能会被多个线程同时抢占，
 * 所以下面的程序会出现 ArrayIndexOutOfBoundsException 的问题。
 */
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("售票了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
