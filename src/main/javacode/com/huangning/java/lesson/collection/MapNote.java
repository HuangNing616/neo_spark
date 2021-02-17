package com.huangning.java.lesson.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: 黄宁
 * Date: 2021/02/07
 * Func: 了解Map
 */
public class MapNote {
    public static void main(String[] args) {

        // 创建map
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();

        // 初始化map
        m2.put("A", 1);
        m2.put("B", 2);
        Map<String, Integer> m3 = new HashMap<>(m1);

        // 将m2中的元素全部添加到m3中
        m3.putAll(m2);

        // 添加元素
        m1.put("one", 1);
        m1.put("two", 2);
        m1.put("three", 3);
        m1.put("four", 4);
        // 覆盖元素并返回旧元素
        Integer one = m1.put("one", 100);
        System.out.println("被覆盖的元素值: " + one);

        // 获取key对应的value/null
        Integer two = m1.get("one");
        System.out.println("key=one对应的value = " + two);

        // 删除key和对应的value, 返回对应的value/null
        Integer removeElem = m1.remove("four");
        System.out.println("删除的value = " + removeElem);

        // 判断包含某个键
        System.out.println("map中是否包含key='one': " +m1.containsKey("one"));

        // 判断包含某个值
        System.out.println("map中是否包含value=3: " + m1.containsKey(3));

        // map容量的大小
        System.out.println("map容量的大小 = " + m1.size());

        // map是否为空
        System.out.println("m1是否为空: " + m1.isEmpty());

        // 清空map所有元素
        m3.clear();
    }
}
