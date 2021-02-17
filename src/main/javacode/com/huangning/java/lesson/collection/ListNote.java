package com.huangning.java.lesson.collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: 黄宁
 * Date: 2021/02/17
 * Func: 了解List
 */
public class ListNote {
    public static void main(String[] args) {

        java.util.List<String> l1 = new LinkedList<>();

        // 传统的遍历
        for(int i = 0; i <= 5; i++){
            l1.add("a-" + i);
        }

        // 增强for循环遍历
        for(String elem : l1){
            l1.add("a-" + elem);
        }

        // 添加元素
        l1.add(3, "a1");

        // 获取元素
        System.out.print("索引为2的元素: " + l1.get(2));

        // 更新元素
        String old = l1.set(6, "a200");
        System.out.println("被替换的旧元素: " + old);

        // 元素第一次出现的索引
        System.out.println("第一次出现'a-3'对应的索引为：" + l1.indexOf("a3"));

        // 元素最后一次出现的索引
        System.out.println("最后一次出现'a3'对应的索引为：" + l1.lastIndexOf("a3"));

        // 删除第一次出现的某个元素
        l1.remove("a-1");
        // 删除索引为3的元素
        l1.remove(3);

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
