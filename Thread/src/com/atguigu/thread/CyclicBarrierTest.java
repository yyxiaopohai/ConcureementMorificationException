package com.atguigu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    //集齐龙珠召唤神龙
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{ System.out.println("------召唤神龙------"); });


        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 正在收集："+temp+"颗");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
