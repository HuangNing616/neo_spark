package com.huangning.java.leetcode.stackqueue;

/**
 * Author：Henry
 * Date: 17/04/2020
 * knowledge: 通过 S(P) + s(N) + S(P) - s(N) = total + target =》 s(P) = (total+target)/2
 * Question:
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you
 * have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 */
public class TargetSum {
    public static void main(String[] args) {
        System.out.println("求解目标和");
        TargetSolution so = new TargetSolution();
        int [] arr =  {1, 1, 1, 1, 1};
        int res = so.findTargetSumWays(arr, 3);
        System.out.println(res);
    }
}

class TargetSolution {
    public int findTargetSumWays(int[] nums, int S) {
        int total = 0;
        for(int i : nums){
            total += i;
        }
        if(total< S || (total + S)%2==1) return 0;
        int goal = (total + S)/2;
        int [] arr = new int[goal+1];

        arr[0] = 1;
        for(int num: nums){
            for (int i = goal; i >= num; i--){
                arr[i] += arr[i-num];
            }
        }
        return arr[goal];
    }
}