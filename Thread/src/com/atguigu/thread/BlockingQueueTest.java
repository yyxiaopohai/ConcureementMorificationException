package com.atguigu.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println(blockingQueue.remainingCapacity());
        System.out.println(blockingQueue);

        blockingQueue.take();
        blockingQueue.take();
        System.out.println(blockingQueue.remainingCapacity());
        blockingQueue.take();
        System.out.println(blockingQueue);

//---------------------------------------------------------------------
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));*/
        //System.out.println(blockingQueue.offer("d"));

        //添加会抛异常
        //System.out.println(blockingQueue.add("s"));


        /*System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/



        /**
         *  阻塞
         *      put();
         *      take();
         */

        //---------------------------------------------------------------
        /**
         *  添加失败返回false,删除失败返回null
         *      offer();
         *      poll();
         *      peek();
         */

        //---------------------------------------------------------------
        /**
         *  抛异常组合
         *      add();
         *      remove();
         *      element();
         */

    }
}
