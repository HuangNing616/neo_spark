package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/20
 * Target: 线程的Yield方法
 * Note: 让出CPU，给其他线程执行的机会
 */

public class ThreadPriority {
    public static void main(String[] args) {

        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());

        t1.setPriority(Thread.NORM_PRIORITY + 3);

        t1.start();
        t2.start();
    }
}

class T1 implements Runnable{
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("com.huangning.java.lesson.thread.T1:" + i);
        }
    }
}

class T2 implements Runnable{
    public void run(){
        for(int i = 0; i<1000 ; i ++){
            System.out.println("--------com.huangning.java.lesson.thread.T2:" + i);
            }
    }
}


