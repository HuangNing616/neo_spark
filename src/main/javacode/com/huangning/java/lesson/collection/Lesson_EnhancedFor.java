package com.huangning.java.lesson.collection;

import java.util.ArrayList;
import java.util.Collection;

public class Lesson_EnhancedFor {
    public static void main(String[] args) {
        // 静态初始化
        int [] arr = {1,2,3,4,5};

        // jdk1.5之后才有的方法
        for(int i : arr){
            System.out.println(i);
        }

        Collection c = new ArrayList();
        c.add(new String("aaa"));
        c.add(new String("bbb"));
        c.add(new String("ccc"));

        for (Object o : c){
            System.out.println(o);
        }

    }
}
