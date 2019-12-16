package com.atguigu.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 走人");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //main线程在这等着上面倒计时结束才能执行
        countDownLatch.await();
        //让main线程最后一个执行---火箭倒计时
        System.out.println(Thread.currentThread().getName()+"\t 班长锁门");
    }
}
