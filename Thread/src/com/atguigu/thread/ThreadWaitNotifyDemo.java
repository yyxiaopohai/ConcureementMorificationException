package com.atguigu.thread;

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {

        //在高内聚低耦合的前提下 线程操纵资源类

        Thread2 thread2 = new Thread2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    thread2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

//        Thread.sleep(3000);
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    thread2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    thread2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start(); new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    thread2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}

class Thread2{
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        if (number != 0) {

            this.wait();
        }
        ++number;
        //通知
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //判断
        if (number == 0) {
            this.wait();
        }
        //干活
        --number;
        //通知
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
}