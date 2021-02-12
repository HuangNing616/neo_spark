package com.huangning.java.leetcode.StackQueue;

import java.util.Stack;

/**
 * Author：Henry
 * Date: 12/04/2020
 * knowledge: 1. 这道题容易犯的小错误就是会增加一个最小值作为属性，入栈可以判断最小值，但是出栈之后的最小值就无法判断了，所以需要一个辅助栈记录最小值
 *            2. RuntimeException：可以不使用try…catch进行处理，但是如果有异常产生，则异常将由JVM进行处理。
 *            3. Exception：在程序中必须使用try…catch进行处理
 *            对于RuntimeException的子类最好也使用异常处理机制。虽然RuntimeException的异常可以不使用try…catch进行处理，但是如果一旦发生异常，则肯定会
 *            导致程序中断执行，所以，为了保证程序再出错后依然可以执行，在开发代码时最好使用try…catch的异常处理机制进行处理。
 *            4. == 比较的就是两个数，如果是对象的话比较的就是两个引用，如果用equal的话比较的也是引用，但是不同的类对equal进行了重写，比如基础数据类型的包装类，还有String类
 *            5. 两个 peek() 方法返回的都是 Integer 类型 ，它们的比较不能用 ==，因为 == 用于包装类型
 * Question:
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack {
    public static void main(String []args){
        TTMinStack tt = new TTMinStack();
        tt.push(2);
    }
}

//// method 1 同步栈
//// time complexity o(1)
//// space complexity o(n)
//class TTMinStack {
//
//    // 数据栈
//    private Stack<Integer> data;
//    // 辅助栈
//    private Stack<Integer> helper;
//
//    /** initialize your data structure here. */
//    public TTMinStack() {
//        data = new Stack<>();
//        helper = new Stack<>();
//    }
//
//    // 思路 1：数据栈和辅助栈在任何时候都同步
//    public void push(int x) {
//        // 数据栈和辅助栈一定会增加元素
//        data.push(x);
//        if(helper.isEmpty()||helper.peek()>=x){
//            helper.push(x);
//        }
//        else {
//            helper.push(helper.peek());
//        }
//    }
//
//    public void pop() {
//        if(!data.isEmpty()){
//            data.pop();
//            helper.pop();
//        }
//    }
//
//    public int top() {
//        if(!data.isEmpty()){
//            return data.peek();
//        }
//        throw new RuntimeException("栈中元素为空，此操作非法");
//    }
//
//    public int getMin() {
//        if(!data.isEmpty()){
//            return helper.peek();
//        }
//        throw new RuntimeException("栈中元素为空，此操作非法");
//    }
//}

class TTMinStack {

    // 数据栈
    private Stack<Integer> data;
    // 辅助栈
    private Stack<Integer> helper;

    /** initialize your data structure here. */
    public TTMinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    // 思路 2：辅助栈和数据栈不同步
    // 关键 1：辅助栈的元素空的时候，必须放入新进来的数
    // 关键 2：新来的数小于或者等于辅助栈栈顶元素的时候，才放入（特别注意这里等于要考虑进去）
    // 关键 3：出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈，即"出栈保持同步"就可以了

    public void push(int x) {
        // 辅助栈在必要的时候才增加
        data.push(x);
        if(helper.isEmpty()||helper.peek()>=x){
            helper.push(x);
        }
    }

    public void pop() {

        if(!data.isEmpty()) {

            // 注意：声明成 int 类型，这里完成了自动拆箱，从 Integer 转成了 int，因此下面的比较可以使用 "==" 运算符
            // 参考资料：https://www.cnblogs.com/GuoYaxiang/p/6931264.html
            // 如果把 top 变量声明成 Integer 类型，下面的比较就得使用 equals 方法

            int top = data.pop();
            if (top == helper.peek()) {
                helper.pop();
            }
        }
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {
        if(!data.isEmpty()){
            return helper.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }
}