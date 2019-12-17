package com.atguigu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {

        //---------------------------线程池的三种格式---------------------------------------
        //ExecutorService executorService = Executors.newFixedThreadPool(3);//固定n个线程的线程池
        //ExecutorService executorService = Executors.newSingleThreadExecutor();//一个线程的线程池，相当于synchronized 锁
        //ExecutorService executorService = Executors.newCachedThreadPool();//不定数量的线程池

        //----------------------------通过线程池买票--------------------------------------------
        Ticker33 ticker33 = new Ticker33();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i <=35; i++) {
                executorService.execute(()->{ticker33.send();});
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
        //-------------------------手动创建线程池------------------------------------------
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        try {
            for (int i = 1; i <=9; i++) {
                final int temp = i;
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 正在卖"+temp+"\t 张");
                });
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }*/
    }
}

class Ticker33{
    private static int ticker = 30;
    private Lock lock = new ReentrantLock();

    public void send(){
        lock.lock();
        try {
            if (ticker>0){
                System.out.println(Thread.currentThread().getName()+"\t 正在卖第:"+(ticker--)+"张\t 剩余："+ticker);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}