package com.huangning.java.lesson.collection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *  Author: 黄宁
 *  Date: 2021/02/17
 *  Func: 了解asList
 *  Target:
 *  1. 可以将变长参数或者数组转换成List
 *  2. asList接收泛型的变长参数
 *  3. 基本类型是不能泛型化的, 如果想要泛型化可以转成对应的包装类
 *  4. 因为数组是对象, 因此可以泛型化
 */
public class ArrayNote {
    public static void main(String []args) {

        int[] a = {1, 2, 3};
        Integer[] b = {1, 2, 3};

        //将整个int类型的数组作为T类型进行转换
        List l1 = Arrays.asList(a);
        List l2 = Arrays.asList(1,2,3);
        List l3 = Arrays.asList(b);

        System.out.println("int型数组转成list的长度:" + l1.size());
        System.out.println("将参数直接转成list的长度:" + l2.size());
        System.out.println("包装类数组转成list的长度:" + l2.size());

    }
}


