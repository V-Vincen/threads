package com.example._exercise.exercise_ticket;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有 N 张火车票，每张票都有一个编号，同时又10个窗口对外售票，请写一个模拟程序。
 * <p>
 * 分析下面的程序可能会产生那些问题？
 * 重复销售？超量销售？
 * <p>
 * synchronized：加了同步代码块，锁定为原子操作。所以不会出错。
 */
public class TicketSeller3 {
    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0) break;

                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("销售了--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }


}
