package com.huangning.java.leetcode.StackQueue;

import java.util.Stack;

/**
 * Author: Henry
 * Date: 14/04/2020
 * knowledge :  1. Integer.parseInt 将字符串转换成整数
 *              2. 数字的ascii码的范围是48 - 57
 *              3. 如何判断两位的字符串是否是数字
 *              4. 根据逆波兰表达式的有效性，那么用于构造stack的数组数组最大长度为tokens.length / 2 + 1
 *              5. switch代替if-else，效率优化(switch 比 if - else 更快)
 *              6. Integer.parseInt代替Integer.valueOf, 减少拆箱装箱操作
 *              7. 自动装箱（就是将基础数据类型转换成对象，因为集合里面的元素是对象类型）用Integer.valueOf等，而自动拆箱一般用intValue等
 *              自动装箱的典型例子还有ArrayList创建的时候，里面添加基本数据类型的元素add（.）, 就会将相应元素自动转换成基础数据类型
 *              自动装箱的发生时机：1. 赋值时，2. 方法调用时
 *              自动装箱的缺点：容易引发内存浪费，比如在循环中，可能自动拆箱装箱操作会自动创建很多对象
 *              8. Integer.parseInt是将字符串转换成基本数据类型int，而Integer.valueOf是将字符串转换成Integer的对象，如果转换成基本数据类型还需要进行
 *              intValue的拆箱操作
 *              9. Integer.valueof中得到的对象有时是同一个对象，有时是不同的对象，要根据把s字符串解析的整数值的大小进行决定：如果s字符
 *              串对应的整数值在 -128~127之间，则解析出的Integer类型的对象是同一个对象；如果s字符串对应的整数值不在-128~127之间，
 *              则解析出的Integer类型的对象不是同一个对象，不管对象是否相等，对象中的value值是相等的，判断其中的value值是否相同是通过Integer的equal方法，而
 *              单纯判断是否是同一个对象，只需要==就可以判读
 *
 *         String s = "100";
 *
 *              //before autoboxing
 *              Integer iObject = Integer.valueOf(3);
 *              Int iPrimitive = iObject.intValue()
 *
 *              //after autoboxing
 *              Integer iObject = 3; //autobxing - primitive to wrapper conversion
 *              int iPrimitive = iObject; //unboxing - object to primitive conversion
 * function:
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate
 * to a result and there won't be any divide by zero operation.
 */

public class EvaluateReversePolishNotation {
    public static void main(String []args){
        RPNSolution so = new RPNSolution();
        String[] tokens = {"2", "1", "+", "3", "*"};
        int res = so.evalRPN(tokens);
        System.out.println(res);

    }
}

// method 1: using array instead of stack
//class RPNSolution {
//    public int evalRPN(String[] tokens) {
//        // get the max length of the stack
//        int stacklength = tokens.length/2 + 1;
//        int [] stack = new int[stacklength];
//        int index = 0;
//        for(int i = 0; i < tokens.length; i++){
//            switch (tokens[i]){
//                case "+":
//                        stack[index-2] += stack[--index];
//                        break;
//                case "-":
//                        stack[index-2] -= stack[--index];
//                        break;
//                case "*":
//                        stack[index-2] *= stack[--index];
//                        break;
//                case "/":
//                        stack[index-2] /= stack[--index];
//                        break;
//                default:
//                        int value = Integer.parseInt(tokens[i]);
//                        stack[index] = value;
//                        index ++;
//            }
//        }
//        return stack[0];
//    }
//}

// method 2: using stack
class RPNSolution {
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        int index = 0;
        int a,b;
        for(int i = 0; i < tokens.length; i++){
            switch (tokens[i]){
                case "+":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a+b);
                        break;
                case "-":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a-b);
                        break;
                case "*":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a*b);
                        break;
                case "/":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a/b);
                        break;
                default:
                        int value = Integer.parseInt(tokens[i]);
                        stack.push(value);
            }
        }
        return stack.peek();
    }
}

