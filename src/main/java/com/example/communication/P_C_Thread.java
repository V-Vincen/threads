package com.example.communication;


 /**
  * @ProjectName:
  * @Package:        com.example.communication
  * @ClassName:      P_C_Thread
  * @Description:
  *                  线程通讯的应用：
  *                  经典案例：生产者/消费者
  *                  场景：生产者（Productor）将产品交给店员（Clerk）,而消费者（Customer）从店员外取走产品，
  *                  店员一次只能持有固定数量的产品（比如：20），如果生产者试图生成更多的产品，店员会叫生产者听
  *                  一下，如果店中有空位放产品了再通知生产者继续生成；如果店中没有产品了，店员会告诉消费者等一
  *                  下，如果店中有产品了再通知消费者来取走产品。
  *
  *                  分析：
  *                  1) 是否是多线程问题？ 是，生产者线程，消费者线程
  *                  2) 是否有共享数据？ 是，店员（或产品）
  *                  3) 如何解决线程的安全问题？ 同步机制，有三种方法
  *                  4) 是否涉及线程通信？ 是
  *
  *
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/9/7 0:28
  * @Version:        1.0.0
  */
public class P_C_Thread {
     public static void main(String[] args) {
         Clerk clerk = new Clerk();

         Producer producer = new Producer(clerk);
         producer.setName("生产者1");

         Consumer consumer = new Consumer(clerk);
         consumer.setName("消费者1");

         producer.start();
         consumer.start();
     }
}


//资源
class Clerk {

    private int productCount = 0;

    //生成产品
    public synchronized void produceProduct() {
        if (productCount < 20) {
            productCount++;
            System.out.println(Thread.currentThread().getName() + "：开始生产第 " + productCount + " 个产品");

            //唤醒
            notify();
        } else {
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费产品
    public synchronized void consumeProduct() {
        if (productCount > 0) {
            System.out.println(Thread.currentThread().getName() + "：开始消费第 " + productCount + " 个产品");
            productCount--;

            //唤醒
            notify();
        } else {
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


//生成者
class Producer extends Thread {

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + "：开始生成产品......");

        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.produceProduct();
        }
    }
}


//消费者
class Consumer extends Thread {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + "：开始消费产品......");

        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumeProduct();
        }
    }
}

























