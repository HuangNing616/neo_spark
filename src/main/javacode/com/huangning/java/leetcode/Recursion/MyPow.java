package com.huangning.java.leetcode.Recursion;

/**
 * Author: Henry
 * Date: 03/04/2020
 * knowledge: 1. 主要考察幂函数中幂指数为负数的情形, 并且时间复杂度要小于O（n）
 *            2. 考虑边界情况Integer.MIN_VALUE = -2147483648= 2^(-31), 如果直接n=-n, 那么由于-n会直接超过边界，所以赋值无效,
 *            所以不能使用-n=n的这个方法，除非将n取相反数
 *            3. java 中的%得到的结果符号取决于第一元素的符号, 5%3 = 5 - (5/3)*3
 *            4. java 中的取整符号/ 针对于结果为负数情形是上取整， 而python的取整符号//是下取整
 * question:
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 */
public class MyPow {
    public static void main (String []args){
        System.out.println("pdd");
        MyPowSolution so = new MyPowSolution();
        double res = so.myPow(2, -2147483648);
        System.out.println("运行结果为"+res);
        System.out.println(5/3);
    }
}

// time complexity o(logn)
class MyPowSolution {
    public double myPow(double x, int n) {
        if(n == 0){ return 1.0; }
        if(n == 1){ return x; }
        if(n == -1){ return 1/x; }

        double half = myPow(x, n/2);
        double res = myPow(x, n%2);
        return res * half * half;
    }
}

