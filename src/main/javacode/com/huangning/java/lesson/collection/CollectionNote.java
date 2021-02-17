package com.huangning.java.lesson.collection;

import java.util.*;

/**
 * Author: 黄宁
 * Date: 2021/02/17
 * Func: 了解容器的基类collection
 * Note:
 * 1. 常用api有add, remove, contains
 */
public class CollectionNote {
    public static void main(String[] args) {

        Name n1 = new Name("f1", "l1");
        Name n2 = new Name("f1", "l1");
        Name n3 = new Name("f1", "l1");

        // 创建collection对象
        Collection<Object> c = new ArrayList<>();

        // 添加元素
        c.add("hello");
        c.add(100);
        c.add(n1);
        c.add(n2);
        c.add(n3);

        // 判断元素是否存在, 调用重写的equals方法
        System.out.println("当前collection是否包含100: " + c.contains(100));

        // 删除元素
        System.out.println(c.remove(new Name("f1", "l1")));
        System.out.println(c);

        System.out.println("=====HashSet 会忽略自定义的类的equals方法 =========");

        Set d = new HashSet();
        d.add(new Name("f1", "l1"));
        d.add("hello");
        d.add(new Integer(100));
        d.add("hello");
        d.add(new Name("f1", "l1"));
        System.out.println(d);

        System.out.println("==========");
        Set s1 = new HashSet();
        Set s2 = new HashSet();
        s1.add("a");s1.add("b");s1.add("c");
        s2.add("a");s2.add("b");s2.add("d");

        // 将s1中的所有元素放到sn中
        Set sn = new HashSet(s1);
        sn.retainAll(s2);

        Set su = new HashSet(s1);
        su.addAll(s2);

        System.out.println(sn);
        System.out.println(su);
        System.out.println("======================");

        List l3 = new LinkedList();
        l3.add(new Name("Karl", "M"));
        l3.add(new Name("Steven", "Lee"));
        l3.add(new Name("John", "O"));
        l3.add(new Name("Tom", "M"));
        System.out.println(l3);
        Collections.sort(l3);
        System.out.println(l3);


    }
}

class Name implements Comparable{
    private final String firstName;
    private final String lastName;
    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName(){   return firstName;   }
    public String getLastName(){    return lastName;    }
    public String toString(){
        return firstName + " " + lastName;
    }

    public boolean equals(Object obj){
        if (obj instanceof Name){
            Name name = (Name) obj;
            return (firstName.equals(name.firstName) && lastName.equals(name.lastName));

        }
        return super.equals(obj);
    }
    public int hashcode(){
        return firstName.hashCode();
    }

    public int compareTo(Object o) {
        Name name = (Name)o;
        // 调用了string重写的compareTo方法
        int lastCamp = this.lastName.compareTo(name.lastName);
        return lastCamp!=0 ? lastCamp : this.firstName.compareTo(name.firstName);
    }
}