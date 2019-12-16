package com.atguigu.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    public static void main(String[] args) {
        //在高内聚低耦合的条件下，线程操作资源类

        MyCache myCache = new MyCache();

        for (int i = 1; i <=10; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=10; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}


//资源类
class MyCache{

    private Map<String,String> map = new HashMap<>();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key,String value){
        //lock.lock();
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写完了");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //lock.unlock();
            rwl.writeLock().unlock();
        }
    }

    public void get(String key){
        //lock.lock();
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读");
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读完了"+value);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //lock.unlock();
            rwl.readLock().unlock();
        }
    }
}