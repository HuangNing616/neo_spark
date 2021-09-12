package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/20
 * Target: 线程的join方法:
 * Note: 合并某个线程，相当于方法调用
 */
public class ThreadJoin {
    public static void main(String[] args) {

        MyThread2 mt2 = new MyThread2("huang thread");
        mt2.start();

        try {
            mt2.join();
        }catch (InterruptedException ignored){}

        for (int i = 1; i < 100; i++) {
            System.out.println("Main thread i value = " + i);
        }
    }
}


class MyThread2 extends Thread{

    MyThread2(String s){
        super(s);
    }
    public void run(){
        for(int i = 0; i<10 ; i ++){
            System.out.println("i am " + getName());
        }
        try{
            sleep(1000);
        }catch (InterruptedException e){
            System.out.println("com.huangning.java.lesson.thread.MyThread2 was interrupted");
        }
    }
}


