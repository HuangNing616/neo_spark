package com.huangning.java.lesson.base;


import java.util.Collection;
import java.util.LinkedList;

/**
 * Author: Henry
 * Date: 2020-05-06
 * 知识点: 1. 将容器转换成迭代器的时候，删除要采用迭代器的remove，不能使用容器自带的remove，
 * 如果使用会抛出异常：ConcurrentModificationException, 因为在用iterator的时候容器会被锁住，无法进行修改
 *        2. iterator只能用在循环中，其中的remove方法删除的是游标的前一个元素
 */

public class IteratorNote {
    public static void main(String[] args) {

        // 定义容器
        Collection<Name2> c = new LinkedList<>();
        c.add(new Name2("fff1", "llll"));
        c.add(new Name2("f2", "l2"));
        c.add(new Name2("fff3", "lll3"));

        System.out.println(c.contains(new Name2("fff1", "llll")));

        // 去掉名字长度小于3的对象
        c.removeIf(name2 -> name2.getFirstName().length() < 3);
        System.out.println("删除名的长度<3的对象之后的列表为：" + c);
    }
}

class Name2{
    private final String firstName;
    private final String lastName;
    public Name2(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName(){   return firstName;   }
    public String getLastName(){    return lastName;    }
    public String toString(){
        return firstName + " " + lastName;
    }

    public boolean equals(Object obj){
        if (obj instanceof Name2){
            Name2 name = (Name2)obj;
            return (firstName.equals(name.firstName) && lastName.equals(name.lastName));

        }
        return super.equals(obj);
    }
    public int hashcode(){
        return firstName.hashCode();
    }
}