package com.atguigu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {
    public static void main(String[] args) {
        //在高内聚低耦合的条件下，线程操作资源类
        //消费者生产者模式

        LockDemo lockDemo = new LockDemo();

        new Thread(()->{for (int i = 0; i < 10; i++) lockDemo.increment(); },"A").start();
        new Thread(()->{for (int i = 0; i < 10; i++) lockDemo.decrement(); },"B").start();
        new Thread(()->{for (int i = 0; i < 10; i++) lockDemo.increment(); },"C").start();
        new Thread(()->{for (int i = 0; i < 10; i++) lockDemo.decrement(); },"D").start();
    }
}

class LockDemo{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //判断-- 干活-- 通知

    public void increment(){
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}