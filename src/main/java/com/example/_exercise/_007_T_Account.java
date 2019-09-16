package com.example._exercise;


import java.util.concurrent.TimeUnit;

//对业务写方法加载
//对业务读方法不加锁
//容易产生脏读问题（dirtyRead）
public class _007_T_Account {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public synchronized double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        _007_T_Account account = new _007_T_Account();

        new Thread(() -> account.set("zhangsan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));
    }
}









