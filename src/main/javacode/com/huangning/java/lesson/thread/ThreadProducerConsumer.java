package com.huangning.java.lesson.thread;

/**
 * Author: huangning
 * Date: 2020/12/21
 * Target: 样例
 */
public class ThreadProducerConsumer{

    public static void main(String[] args) throws Exception {
        SyncStack ss = new SyncStack();
        Producer p = new Producer(ss);
        Consumer c = new Consumer(ss);

        new Thread(p).start();
//        new Thread(p).start();
//        new Thread(p).start();
        new Thread(c).start();
    }
}

class WoTou{
    int id;
    WoTou(int id){
        this.id = id;
    }

    // 重写toString方法
    public String toString(){
        return "com.huangning.java.lesson.thread.WoTou :" + id;
    }
}

class SyncStack{

    int index = 0;
    WoTou[] arrWT = new WoTou[6];

    public synchronized void push(WoTou wt){
        // if 比较危险
        if(index == arrWT.length){
            // 是线程wait，前提必须是线程一定要拿到锁
            // wait 锁就没了
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notify();
        arrWT[index] = wt;
        index ++;
    }

    public synchronized WoTou pop(){
        if(index == 0){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        return arrWT[index];
    }
}

class Producer implements Runnable{
    SyncStack ss = null;
    Producer(SyncStack ss){
        this.ss = ss;
    }
    public void run(){
        for(int i = 0; i< 20; i++){
            WoTou wt = new WoTou(i);
            System.out.println("生产了:" + wt.id);
            ss.push(wt);
            try {
                Thread.sleep((int) (Math.random() * 2));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    SyncStack ss = null;
    Consumer(SyncStack ss){
        this.ss = ss;
    }
    public void run(){
        for(int i = 0; i< 60; i++){
            WoTou wt = ss.pop();
            System.out.println("消费了:" + wt.id);
            try {
                Thread.sleep((int) (Math.random() * 1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
