package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/21
 * Target: 样例
 */
public class ThreadCase implements Runnable{
    int b = 100;

    public synchronized void m1() throws Exception{

        Thread.sleep(3000);
        b = 5000;
        System.out.println("child thread b = " + b);
    }

    public synchronized void m2() throws Exception{
        Thread.sleep(1500);
        b = 1000;
    }

    public void run(){
        try {
            m1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadCase tt = new ThreadCase();
        Thread t = new Thread(tt);
        t.start();

        tt.m2(); // 方法被执行，锁定当前对象
        System.out.println("main thread b = " + tt.b);
    }

}
