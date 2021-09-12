package com.huangning.java.leetcode.recursion;

import java.util.LinkedList;
import java.util.List;

/**
 *  Author: Henry
 *  Date: 06/04/2020
 *  Knowledge: 解决此题最关键的步骤就是每次创建一棵新树，都要重新创建一个根节点，即使两棵树的根节点的值相同，否则会导致
 *  Question:
 Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 */
public class UniqueBinarySearchTreesII {
    public static void main(String []args){
        LinkedList<String> list = new LinkedList<>();
        UniqueSolution so = new UniqueSolution();
        List<UniqueTreeNode> res = so.generateTrees(2);
        System.out.println(res);
    }
}


/**
 * Definition for a binary tree node.
 */
class UniqueTreeNode {
    int val;
    UniqueTreeNode left;
    UniqueTreeNode right;
    UniqueTreeNode(int x) { val = x; }
}

class UniqueSolution {
    public List<UniqueTreeNode> generateTrees(int n) {
        LinkedList<UniqueTreeNode> list = new LinkedList<>();
        if(n==0) return list;
        return helper(1, n);

    }
    public LinkedList<UniqueTreeNode> helper(int start, int end){
        LinkedList<UniqueTreeNode> currentlist  = new LinkedList<>();
        if(start>end){
            currentlist.add(null);
            return currentlist;
        }
        // traversal all the root node value from start to end
        for(int i = start; i<=end; i++){

            // store all the types of the subtree by linklist
            LinkedList<UniqueTreeNode> lefttree = helper(start, i-1);
            LinkedList<UniqueTreeNode> righttree = helper(i+1, end);
            // travelsal the leftsubtree and rightsubtree
            for (UniqueTreeNode l: lefttree){
                for (UniqueTreeNode r: righttree){
                    // initialize the root value , the most important step！！！！！
                    UniqueTreeNode currentroot = new UniqueTreeNode(i);

                    currentroot.left = l;
                    currentroot.right = r;
                    currentlist.add(currentroot);
                }
            }
        }
        return currentlist;
    }
}