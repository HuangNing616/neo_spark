package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/20
 * Target: 线程的Yield方法
 * Note: 让出CPU，给其他线程执行的机会
 */

public class ThreadYield {
    public static void main(String[] args) {

        MyThread3 t1 = new MyThread3("t1");
        MyThread3 t2 = new MyThread3("t2");

        t1.start();
        t2.start();
    }
}


class MyThread3 extends Thread{

    MyThread3(String s){
        super(s);
    }
    public void run(){
        for(int i = 0; i<100 ; i ++){
            System.out.println(getName() + ":" + i);
            if(i % 10 == 0){
                Thread.yield();
            }
        }
    }
}


