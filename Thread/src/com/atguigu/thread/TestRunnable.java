package com.atguigu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestRunnable {
    //常量

    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();

        new Thread(()->{ for (int i = 0; i < 34; i++) ticket2.sale();},"A").start();
        new Thread(()->{ for (int i = 0; i < 34; i++) ticket2.sale();},"B").start();
        new Thread(()->{ for (int i = 0; i < 34; i++) ticket2.sale();},"C").start();

    }
}

class Ticket2 {

    private int ticker = 30;
    private Lock lock = new ReentrantLock();


    public void sale() {
        lock.lock();
        try {
            if (ticker>0){
                System.out.println(Thread.currentThread().getName()+"\t 卖的第："+(ticker--)+"\t还剩："+ticker);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {

            });
        }

    }
}