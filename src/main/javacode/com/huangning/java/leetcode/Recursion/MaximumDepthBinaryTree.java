package com.huangning.java.leetcode.Recursion;

/**
 * Author: Henry
 * Date: 02/04/2020
 *
 * question:
 Given a binary tree, find its maximum depth.
 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 Note: A leaf is a node with no children.

 Example:
 Given binary tree [3,9,20,null,null,15,7],
      3
     / \
    9  20
      /  \
     15   7
 return its depth = 3.
 */
public class MaximumDepthBinaryTree {
    public static void main(String []args){
        MaxDepthTreeNode node1 = new MaxDepthTreeNode(0);
//        MaxDepthTreeNode node2 = node1.left = new MaxDepthTreeNode(9);
//        MaxDepthTreeNode node3 = node1.right = new MaxDepthTreeNode(20);
//        node3.left = new MaxDepthTreeNode(15);
//        node3.right = new MaxDepthTreeNode(7);

        MaxDepthSolution so = new MaxDepthSolution();
        int res = so.maxDepth(node1);
        System.out.println("这一课二叉树的最大深度为: "+res);
    }
}


/**
 * Definition for a binary tree node.
 * }
 */
class MaxDepthTreeNode {
    int val;
    MaxDepthTreeNode left;
    MaxDepthTreeNode right;

    MaxDepthTreeNode(int x) {
        val = x;
    }
}
//class MaxDepthSolution {
//
//    public int maxDepth(MaxDepthTreeNode root) {
//        if(root == null) {
//            return 0;
//        }
//        return Math.max(helper(root.left, 1) , helper(root.right, 1));
//    }
//
//    public int helper(MaxDepthTreeNode node, int height){
//        if(node==null){
//            return height;
//        }
//        return Math.max(helper(node.left, height+1), helper(node.right, height+1));
//    }
//}

class MaxDepthSolution {

    public int maxDepth(MaxDepthTreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left) , maxDepth(root.right));
    }
}