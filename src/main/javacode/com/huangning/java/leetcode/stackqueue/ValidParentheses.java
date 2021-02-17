package com.huangning.java.leetcode.stackqueue;

import java.util.HashMap;
import java.util.Stack;

/**
 * Author：Henry
 * Date: 13/04/2020
 * knowledge：1. hashmap 插入元素的方式是put（key,value）
 *            2. Java 中成员变量不一定初始化(jvm会帮我们初始化)，局部变量必须初始化
 *            3. 对于java的hashmap而言，通过get方法可以通过键得到相应的值，但是如果没有指定的键就返回null
 *            4. hashmap中可以通过containsKey方法来判断某个键是否在hashmap中
 *
 * Question:
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 *
 */
public class ValidParentheses {
    public static void main(String []args){
        ValidSolution so = new ValidSolution();
        String str = "{()[]}";
        boolean res = so.isValid(str);
        System.out.println(res);

    }
}

class ValidSolution{
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        for(int i = 0; i<s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                if(stack.size()!=0 && map.get(stack.peek())==s.charAt(i)){
                    stack.pop();
                }
                else {
                    return false;
                }
            }
            else{
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}