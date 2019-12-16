package com.atguigu.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                boolean flag = false;
                try {
                    //获得车位
                    semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"\t 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag){
                        //释放
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
