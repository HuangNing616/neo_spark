package com.huangning.java.leetcode.recursion;

/**
 * Author: Henry
 * Date: 04/05/2020
 * knowledge: 负数的补码转源码，是针对补码取反再加一
 * Example:
 On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence
 of 0 with 01, and each occurrence of 1 with 10.
 Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 */

public class KthSymbolinGrammar {
    public static void main(String []args){
        System.out.println("ddd");
        KthSolution so = new KthSolution();
        int res = so.kthGrammar(3,1);
        System.out.println("结果为："+res);
        System.out.println(4&5);
    }
}

class KthSolution {
    public int kthGrammar(int N, int K) {
        if (N==1) return 0;
        return (1 - K%2)^kthGrammar(N-1, (K+1)/2);
    }
}
