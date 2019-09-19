package com.example._exercise.exercise_ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * 有 N 张火车票，每张票都有一个编号，同时又10个窗口对外售票，请写一个模拟程序。
 * <p>
 * 分析下面的程序可能会产生那些问题？
 * 重复销售？超量销售？
 *
 * ArrayList 是线程不安全的，其各个方法也不是同步的。所以可能会出现，重复销售和超量销售的问题！！！
 */
public class TicketSeller1 {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("售票了--" + tickets.remove(0));
                }
            }).start();
        }
    }


}
