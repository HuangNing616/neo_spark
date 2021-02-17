package com.huangning.java.lesson.base;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  Author: 黄宁
 *  Date: 2021/02/17
 *  Func: 增强for循环
 *  Note: 增强for循环是jdk1.5之后才出现的
 */
public class EnhancedFor {
    public static void main(String[] args) {

        int [] arr = {1,2,3,4,5};

        for(int i : arr){
            System.out.println(i);
        }

        ArrayList<String> c = new ArrayList<>();
        c.add("aaa");
        c.add("bbb");
        c.add("ccc");

        for (Object o : c){
            System.out.println(o);
        }

    }
}
