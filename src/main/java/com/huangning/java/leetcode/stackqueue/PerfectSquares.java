package com.huangning.java.leetcode.stackqueue;

/**
 * Author: Henry
 * Date: 10/04/2020
 * question:
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class PerfectSquares {
    public static void main(String []args){
        PerfectSolution per = new PerfectSolution();
        int res = per.numSquares(13);
        System.out.println(res);
    }
}

class PerfectSolution {
    public int numSquares(int n) {
        int [] dp = new int[n+1];

        for(int i=0; i<=n; i++){
            dp[i] = i;
            for(int j=1; i-j*j>=0; j++){
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}