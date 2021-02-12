package com.huangning.java.leetcode.Recursion;

import java.util.LinkedList;
import java.util.List;

/**
 *  Author: Henry
 *  Date: 03/30/2020
 *  Knowledge: 列表获取元素的方式是get
 *  Question:
 *  In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *  Example:
 *
 *  Input: 3
 *  Output: [1,3,3,1]
 *   Follow up:
 *
 *  Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalTriangleII {
    public static void main(String []args){
        PascalTriSolution so = new PascalTriSolution();
        List<Integer> res = so.getRow(4);
        System.out.println(res);

    }
}

//// dp method
//// recursive method
//class PascalTriSolution {
//    public List<Integer> getRow(int rowIndex) {
//        List<Integer> list = new LinkedList<>();
//        list.add(1);
//        if(rowIndex==0){
//            return list;
//        }
//        list.add(1);
//        if(rowIndex==1){
//            return list;
//        }
//        recursion(rowIndex-2, list);
//        return list;
//    }
//    public List<Integer> recursion(int level, List<Integer> list) {
//        if (level < 0) {
//            return list;
//        }
//        list.add(1);
//        int list_length = list.size();
//
//        // compute second first and second last element of list
//        for (int i = list_length - 2; i >= 1; i--) {
//            // modify the element of list by using set
//            list.set(i, list.get(i) + list.get(i - 1));
//        }
//        return recursion(level - 1, list);
//    }
//}

// dp method
// iterative method
class PascalTriSolution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        if (rowIndex == 0) {
            return list;
        }
        list.add(1);
        if (rowIndex == 1) {
            return list;
        }
        while (rowIndex-2>=0){
            list.add(1);
            for(int i = list.size()-2; i>=1; i--){
                list.set(i, list.get(i-1) + list.get(i));
            }
            rowIndex --;
        }
        return list;
    }
}
