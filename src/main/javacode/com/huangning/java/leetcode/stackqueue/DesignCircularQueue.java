package com.huangning.java.leetcode.stackqueue;


/**
 * Author: Henry
 * Date: 7/4/2020
 * knowledge : 1. 根据(headindex + count - 1)/mod capcity 可以获取队尾的索引
 *             2. 给整型数组分配内存之后会自动初始化为0
 * question:
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Your implementation should support following operations:
 *
 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 */
public class DesignCircularQueue {
    public static void main(String []args){
        System.out.println("构造循环队列");
        MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
        circularQueue.enQueue(1);
        circularQueue.enQueue(2);
        circularQueue.enQueue(3);
        circularQueue.enQueue(4);
        circularQueue.Rear();  // return 3
        circularQueue.isFull();  // return true
        circularQueue.deQueue();  // return true
        circularQueue.enQueue(4);  // return true
        circularQueue.Rear();  // return 4
    }
}


class MyCircularQueue {

    public int [] queue;
    private int headindex;
    private int count;
    private int capcity;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.capcity = k;
        this.queue = new int[capcity];
        this.headindex = 0;
        this.count = 0;

    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        // if queue is full, return false
        if(this.count == this.capcity){
            return false;
        }

        // find next one of the tail index
        int nexttail = (this.headindex + this.count) % this.capcity;
        this.queue[nexttail] = value;
        this.count += 1;
        return true;

    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        // if queue is empty, return false
        if(this.count == 0){
            return false;
        }

        this.headindex = (this.headindex + 1) % this.capcity;
        this.count -= 1;
        return false;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(this.count==0){
            return -1;
        }
        return this.queue[this.headindex];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(this.count==0){
            return -1;
        }
        return this.queue[ (this.headindex + this.count-1) % this.capcity];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if(this.count==0) return true;
        else return false;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if(this.count == this.capcity) return true;
        else return false;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */

