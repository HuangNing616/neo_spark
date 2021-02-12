package com.huangning.java.leetcode.BinaryTree;

import java.util.HashMap;

/**
 Author: Henry
 Date: 03/20/2020
 Knowledge: hashmap中的get()方法是通过键来获取值
 Question:
 Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 For example, given
 preorder = [3,9,20,15,7]
 inorder = [9,3,15,20,7]
 Return the following binary tree:
          3
        /  \
       9    20
          /   \
        15     7

 **/
public class PreOrderAndInOrder {
    public static void main(String[] args){
        // construct 2 array to store inorder list and postorder list
        int [] inorder = new int[]{3,9,20,15,7};
        int [] postorder = new int[]{9,3,15,20,7};
        PreAndInSolution so = new PreAndInSolution();
        PreAndInTreeNode res = so.buildTree(inorder, postorder);
        System.out.println(res.val);
    }
}

/**
 * Definition for a binary tree node.
 * }
 */

class PreAndInTreeNode {
    int val;
    PreAndInTreeNode left;
    PreAndInTreeNode right;
    PreAndInTreeNode(int x) { val = x;}
}

class PreAndInSolution {

    HashMap<Integer, Integer> memo = new HashMap<>();
    int []pre;

    public PreAndInTreeNode buildTree(int[] preorder, int[] inorder) {
        // find the rootvalue from the postorder list
        for(int i=0; i<inorder.length; i++){
            memo.put(inorder[i],i);
        }
        pre = preorder;
        return  helper(0, preorder.length-1, 0, inorder.length-1);
    }

    public PreAndInTreeNode helper(int ps, int pe, int is, int ie){
        if(ps>pe||is>ie) return null;

        // get the root of the tree
        PreAndInTreeNode root = new PreAndInTreeNode(pre[ps]);

        //find the rootindex from the intorder
        int ri = memo.get(pre[ps]);

        //watch out the index both startindex and endindex
        root.left = helper(ps+1, ps+ri-is,is,ri-1);
        root.right = helper(ps+ri-is+1, pe,ri+1,ie);

        return root;
    }
}