package com.huangning.java.leetcode.Recursion;

import java.util.HashMap;

/**
 * Author: Henry
 * Date: 03/29/2020
 *
 * question:
 * Climbing Stairs
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */
public class ClimbingStairs {
    public static void main(String []args){
        ClimbingSolution so = new ClimbingSolution();
        int res = so.climbStairs(4);
        System.out.println(res);
    }
}

class ClimbingSolution {
    HashMap<Integer, Integer> memoization = new HashMap<>();
    public int climbStairs(int n) {
        if(n==1||n==2){
            memoization.put(n, n);
            return n;
        }
        return  (memoization.get(n)!=null) ? memoization.get(n) : climbStairs(n-1) + climbStairs(n-2);
    }
}
