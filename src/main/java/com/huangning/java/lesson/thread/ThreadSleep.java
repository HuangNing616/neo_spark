package com.huangning.java.lesson.thread;

import java.util.Date;

/**
 * Author: huangning
 * Date: 2020/12/20
 * Target: 线程的Sleep方法
 * Note:
 * 1. 终止线程暴力程度：stop > interrupt > 修改成员变量
 * 2. run方法结束，线程结束
 */
public class ThreadSleep {
    public static void main(String[] args) {

        MyThread mt = new MyThread();
        mt.start();

        for (int i = 1; i < 100; i++) {
            System.out.println("Main thread i value = ");
        }

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            System.out.println("Main thread has been interrupted");
        }

        // 让子线程结束
        // mt.stop();
        // mt.interrupt();
        mt.shutdown();
    }
}


class MyThread extends Thread{

    private boolean flag = true;
    public void run() {

        System.out.println("当前线程状态是:" + Thread.currentThread().isAlive());
        while (flag){
            System.out.println("com.huangning.java.lesson.thread.MyThread thread ==== " + new Date() + "====");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("com.huangning.java.lesson.thread.MyThread was interrupted");
                return;
            }
        }
    }
    public void shutdown(){
        flag = false;
    }
}


