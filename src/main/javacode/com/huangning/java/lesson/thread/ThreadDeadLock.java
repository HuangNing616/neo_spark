package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/21
 * Target: 线程死锁
 */

public class ThreadDeadLock implements Runnable {

    public int flag = 1;
    static Object o1 = new Object(), o2 = new Object();

    public void run(){
        System.out.println("flag=" + flag);
        if(flag == 1){
            synchronized (o1){
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            synchronized (o2){
                System.out.println("1");
            }
        }
        if (flag == 0){
            synchronized (o2){
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            synchronized (o1){
                System.out.println("O");
            }
        }
    }

    public static void main(String[] args) {

        ThreadDeadLock td1 = new ThreadDeadLock();
        ThreadDeadLock td2 = new ThreadDeadLock();

        td1.flag = 1;
        td2.flag = 0;

        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();
    }


}


