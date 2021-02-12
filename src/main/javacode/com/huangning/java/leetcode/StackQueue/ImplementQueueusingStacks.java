package com.huangning.java.leetcode.StackQueue;

import java.util.Stack;

/**
 * Author: Henry
 * Date: 2020/4/21
 * question:
 * Implement the following operations of a queue using stacks.
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 */
public class ImplementQueueusingStacks {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }
}

class MyQueue {
    Stack<Integer> stack;
    Stack<Integer> helperstack;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        helperstack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // if helper stack is empty ,pop the helperstack, else push the helper and pop the helper
        if(!helperstack.isEmpty()){
            return helperstack.pop();
        }

        int stacklength = stack.size();
        for(int i = 0; i < stacklength; i++){
            int temp = stack.pop();
            helperstack.push(temp);
        }

        return helperstack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(!helperstack.isEmpty()){
            return helperstack.peek();
        }
        return stack.get(0);
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(stack.isEmpty() && helperstack.isEmpty()){
            return true;
        }
        return false;
    }
}

