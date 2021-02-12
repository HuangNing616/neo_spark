package com.huangning.java.lesson.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:Henry
 * Date:2020-05-06
 * 统计输入每个参数出现的次数，也就是WordCount的应用
 * 知识点：静态方法中只能用静态成员变量
 *
 */
public class Lesson_TestArgsWords2 {
    private static final int ONE = 1;
    public void main(String[] args) {

        Map<String, Integer> m = new HashMap<>();

        // 用增强for循环简化代码
        for (String arg : args) {
            Integer freq = m.get(arg);
            m.put(arg, (freq == null ? ONE : freq + 1));
        }
        System.out.println(m.size() + " distinct words detected");
        System.out.println(m);
    }
}

class AAA{
    private static final int TWO = 1;
}
