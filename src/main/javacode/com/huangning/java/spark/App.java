package com.huangning.java.spark;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");
        System.out.println("\n1. Export Map Key to List...");
        System.out.println(map.entrySet());
        ArrayList result = new ArrayList(map.entrySet());
        System.out.println(result);
        List<String> li = new LinkedList<String>();
        li.add("a");
        li.add("b");
        li.add("c");
        li.add("d");
        Collections.reverse(li);
        System.out.println(li);
    }
}
