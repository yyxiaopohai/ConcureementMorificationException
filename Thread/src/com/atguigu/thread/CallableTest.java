package com.atguigu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //这里构造器中需要传Callable
        FutureTask futureTask = new FutureTask(new MyThread());

        //这里FutureTask继承了Rannable接口，所以创建线程可以传进去；
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }
}
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("------进来了------");
        return "yeah";
    }
}