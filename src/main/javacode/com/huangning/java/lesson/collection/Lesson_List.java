package com.huangning.java.lesson.collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Henry
 * Date:2020-5-6
 * Function: List 的各种方法，以及封装了基于List的各种方法的类Collections
 */
public class Lesson_List {
    public static void main(String[] args) {
        List<String> l1 = new LinkedList<>();
        for(int i = 0; i <= 5; i++){
            l1.add("a"+i);
        }

        // 1. add: 添加某个元素
        l1.add(3, "a1");
        System.out.println(l1);

        //2. get: 获取某个位置的元素
        System.out.print("索引为2的元素为" + l1.get(2));

        //3. set: 在指定位置替换原有的元素, 返回旧元素
        String oldValue = l1.set(6, "a200");
        System.out.println("替换后的List：" + l1);
        System.out.println("被替换的旧元素为" + oldValue);

        //4. indexOf: 获取某个元素第一次出现对应的索引
        System.out.println("第一次出现'a3'对应的索引为：" + l1.indexOf("a3"));

        //5. lastIndexOf: 获取某个元素最后一次出现对应的索引
        System.out.println("最后一次出现'a3'对应的索引为：" + l1.lastIndexOf("a3"));

        //6. remove: 产出指定位置的元素，或者删除第一次出现的某个元素
        l1.remove("a1");
        System.out.println("删除第一次出现的元素a1后的l1为：" + l1);
        l1.remove(3);
        System.out.println("删除索引为3的元素后l1为：" + l1);


        // Collections常用方法
        Collections.shuffle(l1);
        System.out.println("随机排列后的类l1: " + l1);

        Collections.reverse(l1);
        System.out.println("反转后的l1为: " + l1);

        Collections.sort(l1);
        System.out.println("排序后的l1为: " + l1);

        System.out.println("二分法查找'a2'对应的索引为: " + Collections.binarySearch(l1,"a2"));

    }
}
