package com.huangning.java.leetcode.StackQueue;

import java.util.Stack;

/**
 * Author：Henry
 * Date：14-02-2020
 * question:
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you
 * would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {
    public static void main(String []args){
        TemperaturesSolution so = new TemperaturesSolution();
        int [] arr ={73, 74, 75, 71, 69, 72, 76, 73};
        int [] res = so.dailyTemperatures(arr);
        for (int i = 0; i<res.length; i++){
            System.out.println(res[i]);
        }
    }

}

class TemperaturesSolution {
    public int[] dailyTemperatures(int[] T) {

        //initialize the stack
        Stack<Integer> stack = new Stack();
        int [] array = new int[T.length];

        for(int i =0; i<T.length; i++){
            while (!stack.isEmpty() && T[stack.peek()] < T[i]){
                int topindex = stack.pop();
                array[topindex] = i - topindex;
            }
            stack.push(i);
        }
        return array;
    }
}
