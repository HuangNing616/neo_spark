package com.huangning.java.leetcode.recursion;

/**
 *  Author: Henry
 *  Date: 03/28/2020
 *  Knowledge:
 *  Question:
 *  Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
    public static void main(String []args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        node2.next = new ListNode(3);
        Solution so = new Solution();
        ListNode res = so.reverseList(node1);
        while (res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}

///**
// * recursive method
// * time complexity O(n)
// * space complexity O(1)
// */
//class Solution {
//    public ListNode reverseList(ListNode head) {
//        if(head==null||head.next==null){
//            return head;
//        }
//        ListNode top = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return top;
//    }
//}

/**
 * iterative method
 * time complexity O(n)
 * space complexity O(1)
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }

        ListNode preptrs=null;
        ListNode nextptrs;
        while (head!=null){
            nextptrs = head.next;
            head.next = preptrs;
            preptrs = head;
            head = nextptrs;
        }
        return preptrs;
    }
}

