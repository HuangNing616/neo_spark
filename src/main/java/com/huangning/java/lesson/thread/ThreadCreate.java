package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/20
 * Target: 线程的创建
 * Note:
 * 1. 创建线程，最好使用实现接口的方式，来达成类多继承的效果
 */
public class ThreadCreate {
    public static void main(String[] args) {

        // 第1种创建线程的方法
        R r = new R();
        Thread tt = new Thread(r);
        tt.start();

        // 第2种创建线程的方法
        T t = new T();
        t.start();

        for (int i = 1; i < 100; i++) {
            System.out.println("Main thread i value = " + i);
        }
    }
}

class R implements Runnable{
    public void run() {
        for (int i = 1; i < 100; i++) {
            System.out.println("com.huangning.java.lesson.thread.R thread j value = " + i);
        }
    }
}

class T extends Thread{

    public boolean flag = true;
    public void run() {
        for (int i = 1; i < 100; i++) {
            System.out.println("com.huangning.java.lesson.thread.T thread k value = " + i);
        }
    }
}

