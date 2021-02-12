package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/20
 * Target: 线程同步
 * Note:
 * 1.同一个Runnable对象，可以创建多个线程
 * 2.执行 synchronized void xxx 方法过程中 当前对象被锁定
 */

public class ThreadSync implements Runnable {

    Timer timer = new Timer();
    public static void main(String[] args) {


        ThreadSync test = new ThreadSync();

        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    public void run(){
        timer.add(Thread.currentThread().getName());
    }
}


class Timer{
    private static int num = 0;
    public synchronized void add(String name){
//        synchronized (this) {
            num++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(name + "你是第" + num + "个使用timer的线程");
    }
}


