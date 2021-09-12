package com.huangning.java.leetcode.binarytree;

import java.util.HashMap;

/**
 Author: Henry
 Date: 03/19/2020
 Knowledge: 1. 静态初始化:初始化时由程序员显式指定每个数组元素的初始值，有系统决定数组的长度；
            eg:
            1） int []arr;
                arr = new int[]{1,2,3,4,5}
            2) 动态初始化：int []arr = new int []{1,2,3,4,5}(简化版)
            2. 初始化时由程序员指定数组的长度，由系统初始化每个数组元素的默认值。
            eg:int []arr = new int[20]
            3. 不要同时使用静态初始化和动态初始化，也就是说，不要在进行数组初始化时，既指定数组的长度，也为每个数组元素分配初始值
            4. 解决该道题的关键就是给中序遍历的列表中的每个值添加上索引
         Question: Construct Binary Tree from Inorder and Postorder Traversal
         Solution
         Given inorder and postorder traversal of a tree, construct the binary tree.
         Note:
         You may assume that duplicates do not exist in the tree.
         For example, given
         inorder = [9,3,15,20,7]
         postorder = [9,15,7,20,3]
         Return the following binary tree:
         3
         / \
         9  20
         /  \
         15   7
 **/
public class InOrderAndPostOrder {
    public static void main(String[] args){
        // construct 2 array to store inorder list and postorder list
        int [] inorder = new int[]{9,3,15,20,7};
        int [] postorder = new int[]{9,15,7,20,3};
        InAndPostSolution so = new InAndPostSolution();
        InAndPostTreeNode res = so.buildTree(inorder, postorder);
        System.out.println(res.val);
    }
}

/**
 * Definition for a binary tree node.
 * }
 */

class InAndPostTreeNode {
    int val;
    InAndPostTreeNode left;
    InAndPostTreeNode right;
    InAndPostTreeNode(int x) { val = x;}
}

class InAndPostSolution {
    HashMap<Integer, Integer> memo = new HashMap<>();
    int []post;
    public InAndPostTreeNode buildTree(int[] inorder, int[] postorder) {
        // find the rootvalue from the postorder list
        for(int i=0; i<inorder.length; i++){
            memo.put(inorder[i],i);
        }
        post = postorder;
        return  helper(0, inorder.length-1, 0,postorder.length-1);
    }
    public InAndPostTreeNode helper(int is, int ie, int ps, int pe){
        if(is>ie|| ps>pe) return null;

        // get the root of the tree
        InAndPostTreeNode root = new InAndPostTreeNode(post[pe]);

        //find the rootindex from the intorder
        int ri = memo.get(post[pe]);

        //watch out the index both startindex and endindex
        root.left = helper(is, ri-1, ps, ps+ri-is-1);
        root.right = helper(ri+1,ie, ps+ri-is, pe-1);

        return root;
    }
}