package com.huangning.java.lesson.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:Henry
 * Date:2020-05-06
 * java中map的常用函数
 */
public class Lesson_Map {
    public static void main(String[] args) {
        Map<String, Integer> m1 = new HashMap<>();

        //1. put： 添加元素, 返回覆盖的值/报空指针异常错
        m1.put("one", 1);
        m1.put("two", 2);
        m1.put("three", 3);
        m1.put("four", 4);

        //2. get: 返回指定key对应的value/null
        Integer two = m1.get("one");
        System.out.println("one对应的value = " + two);

        //3.remove: 删除指定key和相应的value, 返回对应的value/null
        Integer removeElem = m1.remove("four");
        System.out.println("删除的value = " + removeElem);

        //4.containsKey: 是否包换某个键
        System.out.println("m1中是否包含key = 'one'：" +m1.containsKey("one"));

        //5.containsValue: 是否包换某个值
        System.out.println("m1中是否包含value = 3：" + m1.containsKey(3));

        //6.size: map中数据对的数量
        System.out.println("m1容量的大小 = " + m1.size());

        //7.isEmpty: map是否为空
        System.out.println("m1是否为空: " + m1.isEmpty());

        Map<String, Integer> m2 = new HashMap<>();
        m2.put("A", 1);
        m2.put("B", 2);

        // 通过传入map引用来初始化
        Map<String, Integer> m3 = new HashMap<>(m1);

        // 8. putAll:将m2中的元素全部添加到m3中
        m3.putAll(m2);
        System.out.println("添加另外一个map的所有元素: " + m3);

        // 9. 清空map中的所有元素
        m3.clear();
        System.out.println("清空的map为: " + m3);
        System.out.println("m3是否为空: " + m3.isEmpty());
    }
}
