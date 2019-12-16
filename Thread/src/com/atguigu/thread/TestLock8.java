package com.atguigu.thread;

import java.util.concurrent.TimeUnit;

public class TestLock8 {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 题目：  多线程8锁
         * 1.   标准访问，先打印邮件还是短信
         * 2.   email方法新增暂停3秒钟，请问先打邮件还是短信
         * 3.   新增普通hello方法，请问先打印邮件还是hello
         * 4.   两部手机，请问先打印邮件还是短信
         * 5.   两个静态同步方法，1部手机，请先打印邮件还是短信
         * 6.   两个静态同步方法，2部手机，请问先打印邮件还是短信
         * 7.   1个普通同步方法，1个静态同步方法，1部手机请问先打邮件还是短信
         * 8.   1个普通同步方法，1个静态同步方法，2部手机请问先打印邮件还是短信
         *
         *
         * 总结：同步方法相当于锁的当前这一个手机
         *
         *       静态同步方法相当于锁的是生产手机的工厂
         *
         *       普通方法相当于手机的手机壳
         */
        Phone2 phone2 = new Phone2();
        Phone2 phone21 = new Phone2();

        new Thread(()->{
            try {
                phone2.sendEmail();
                //phone2.sendSSS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        //TimeUnit.SECONDS.sleep(2);
        new Thread(()->{

            //phone2.sendSMS();
            phone21.sendSMS();
            //phone2.sendSSS();
            },"B").start();
    }
}

class Phone2{
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("------sendEmail------");
    }
    public synchronized void sendSMS(){

        System.out.println("------sendSMS------");
    }
    public void sendSSS(){
        System.out.println("------sss------");
    }
}

@FunctionalInterface
interface Soo{
    public void soo();
}