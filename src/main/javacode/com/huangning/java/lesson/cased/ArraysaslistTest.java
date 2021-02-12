package com.huangning.java.lesson.cased;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *  Author: Henry
 *  Date: 03/25/2020
 *  Knowledge: Arrays.asList()
 *             1. 可以将一个变长参数或者数组转换成List
 *             2. 学会泛型和内部类
 *
 */
public class ArraysaslistTest {
    public static void main(String []args) {
        int[] a = {1, 2, 3};
        Integer[] b = {1, 2, 3};
        System.out.println(a[0]);

        //把一个int类型的数组作为了T的类型，所以转换后在List中就只有一个类型为int数组的元素。
        List listA = Arrays.asList(a);
        List listA1 = Arrays.asList(1,2,3);
        List listB = Arrays.asList(b);

        System.out.println(listA.size());//out:1
        System.out.println(listA1.size());//out:3
        System.out.println(listB.size());//out:3



        // asList接收的是一个泛型变长参数，基本类型是不能泛型化的，就是说8种基本类型不能作为泛型参数，要想作为泛型参数就要使用其所对应的包装类
        // 数组是一个对象，它是可以泛型化的

        System.out.println("ListA元素类型："+listA.get(0).getClass());
        String [] str = {"cat", "dog"};
        System.out.println(Arrays.asList(str).getClass());
        List<String> pets = new LinkedList<>(Arrays.asList(str));
        pets.add("dd");
        System.out.println(pets);
    }
}


