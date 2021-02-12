package com.huangning.java.leetcode.StackQueue;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Author: Henry
 * Date: 9/4/2020
 * knowledge : 1. 根据'3'-'0'来获取3-0的数值，原理是通过ascii码的相对距离来计算差值
 *             2. 字符和数字运算就是相应的ascii码和数字运算
 *             3. substring切片和python的列表切片是一样的
 *             4. 将数字a变成字符串可以通过""+a来转化
 *             5. 字符串和null比较的时候不要用equal，而是用null
 * question:
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6',
 * '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock
 * will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of
 * turns required to open the lock, or -1 if it is impossible.
 */
public class OpentheLock {
    public static void main(String []args){
        System.out.println("hhhhh");
        System.out.println('0'+100);
        String  str = "dasba";
        String  sss =  "dasba";
        OpenSolution so = new OpenSolution();
        String [] deadline = new String[1];
        deadline[0] = "8888";
        String target = "0009";
        int res = so.openLock(deadline, target);
        System.out.println(res);
    }
}

class OpenSolution {
    public int openLock(String[] deadends, String target) {
        // convert deadend array to Set
        Set<String> deadset = new HashSet<>();
        for(String i : deadends) deadset.add(i);

        Queue<String> queue = new LinkedList<>();
        int path = 0;

        // set the marked set to avoid duplicated judgement
        Set<String> seen = new HashSet<>();
        seen.add("0000");

        // add first code to the queue
        queue.offer("0000");
        queue.offer(null);

        while (!queue.isEmpty()){
            String code = queue.poll();
            if(code == null){
                path ++;
                // 利用peek的性质，当队列中不存在元素的时候，peek返回的就是none值
                if(queue.peek() != null){
                    queue.offer(null);
                }
            }
            else if(code.equals(target)){
                return path;
            }
            else if(!deadset.contains(code)){
                for(int i=0; i<4; i++) {
                    for (int j = -1; j <= 1; j += 2) {
                        int temp = (code.charAt(i) - '0' + j + 10) % 10;
                        String curstr = code.substring(0, i) + ("" + temp) + code.substring(i + 1, 4);
                        if (!seen.contains(curstr)) {
                            seen.add(curstr);
                            queue.add(curstr);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
