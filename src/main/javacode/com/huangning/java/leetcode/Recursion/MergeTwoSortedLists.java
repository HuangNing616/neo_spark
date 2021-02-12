package com.huangning.java.leetcode.Recursion;

/**
 * Author: Henry
 * Date: 04/04/2020
 * knowledge: 递归问题需要考虑的事情：
 *            1. 定义递归函数
 *            2. 递归关系和基本情况
 *            3. 利用备忘录减少重复计算次数
 *            4. 利用尾递归减少空间复杂度
 * question:
 Merge two sorted linked lists and return it as a new list.
 The new list should be made by splicing together the nodes of the first two lists.
 Example:
 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
    public static void main(String []args){
        MergeListNode l1 = new MergeListNode(1);
        l1.next = new MergeListNode(2);
        MergeListNode l2 = new MergeListNode(1);
        l2.next = new MergeListNode(3);
        MergeSolution so = new MergeSolution();
        MergeListNode res = so.mergeTwoLists(l1,l2);
        while (res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}


/**
 * Definition for singly-linked list.
 */
class MergeListNode {
    int val;
    MergeListNode next;
    MergeListNode(int x) { val = x; }
}

class MergeSolution {
    public MergeListNode mergeTwoLists(MergeListNode l1, MergeListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        if(l1.val<=l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}