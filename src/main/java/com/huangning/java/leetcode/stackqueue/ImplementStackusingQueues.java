package com.huangning.java.leetcode.stackqueue;


/**
 * Author:Henry
 * Date:2020-04-22
 * question:
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 *
 */

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackusingQueues {
    public static void main(String[] args) {
        System.out.println("不用测试");
        MyStack stack = new MyStack();
        stack.push(2);
        stack.push(2);
        stack.push(2);
        stack.pop();   // returns 2
        stack.pop();   // returns 2
        stack.pop();   // returns 2

//        System.out.println(stack.top());
//        stack.top();   // returns 2
//        stack.pop();   // returns 2
        stack.empty(); // returns false
    }
}

// 两个队列，压入元素O(1), 弹出O(n)
class MyStack {

    private Queue<Integer> queue;
    private Queue<Integer> helper;
    private int top;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
        helper = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (queue.size()>1){
            top = queue.remove();
            helper.add(top);
        }
        int res = queue.remove();
        Queue<Integer> temp = queue;
        queue = helper;
        helper = temp;
        return res;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        if(queue.isEmpty()){
            return true;
        }
        return false;
    }
}