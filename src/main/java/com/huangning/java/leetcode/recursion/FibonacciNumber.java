package com.huangning.java.leetcode.recursion;


import java.util.HashMap;

/**
 *  Author: Henry
 *  Date: 03/31/2020
 *  Knowledge:
 *  Question:
 The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

 F(0) = 0,   F(1) = 1
 F(N) = F(N - 1) + F(N - 2), for N > 1.
 Given N, calculate F(N).
 */
public class FibonacciNumber {
    public static void main(String []args){
        FibonacciSolution so = new FibonacciSolution();
        int res = so.fib(4);
        System.out.println("结果是"+res);
    }
}

class FibonacciSolution {
    HashMap<Integer, Integer> memoization = new HashMap<>();
    public int fib(int N) {
        // bottom case
        if(N==0|| N==1){
            memoization.put(N, N);
            return N;
        }
        return (memoization.get(N)!=null)? memoization.get(N): (fib(N-1)+fib(N-2));
    }
}
