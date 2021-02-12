package com.huangning.java.leetcode.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 Author: Henry
 Date: 03/20/2020
 Knowledge: hashmap中的get()方法是通过键来获取值
 Question:
 You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 The binary tree has the following definition:
 struct Node {
 int val;
 Node *left;
 Node *right;
 Node *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 Initially, all next pointers are set to NULL.
 Follow up:
 You may only use constant extra space.
 Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 **/
public class PopulatingNextRightPointers {
    public static void main(String []args){
        Node node = new Node(2);
        node.left = new Node(3);
        node.right = new Node(3);
        PopulatingNextRightPointersSolution so = new PopulatingNextRightPointersSolution();
        Node res = so.connect(node);
        System.out.println(res.val);
    }
}

/**
Definition for a Node.
*/
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


class PopulatingNextRightPointersSolution {
    public Node connect(Node root) {

        // check the special situation
        if(root==null){
            return root;
        }

        // Initialize a queue data structure which contains just the root of the tree
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Outer while loop which iterates over each level
        while (!queue.isEmpty()){

            // Iterate over all the nodes on the current level
            int size = queue.size();

            for(int i = 0; i < size; i++){
                // Pop a node from the front of the queue
                Node node = queue.poll();

                // size ensure we can not establish next pointers beyond the end of a level
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // add the child to the back of the queue
                if(node.left !=null){
                    queue.add(node.left);

                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        return root;
    }
}
