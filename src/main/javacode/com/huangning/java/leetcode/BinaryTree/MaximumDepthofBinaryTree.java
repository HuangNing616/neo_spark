package com.huangning.java.leetcode.BinaryTree;


/**
 Author: Henry
 Date: 03/17/2020
 Question: Given a binary tree, find its maximum depth.
 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 Note: A leaf is a node with no children.
 knowledge:1. 在使用变量时需要遵循的原则为：就近原则,即首先在局部范围找，有就使用；接着在成员位置找。
           2. 成员变量和局部变量的区别：
            (1) 成员变量定义在类中，在整个类中都可以被访问, 而局部变量定义在局部范围内
            (2) 成员变量有默认初始化，而局部变量没有
            (3) 成员变量存在对内存中，随着对象的产生和消失而产生和消失，作用范围结束变量空间会自动释放。

 Example:

 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its depth = 3
 **/
public class MaximumDepthofBinaryTree {
    public static void main(String []args){
        // create a object of LevelorderTreeNode
        MaxDepthTreeNode node = new MaxDepthTreeNode(3);
        node.left = new MaxDepthTreeNode(9);
        MaxDepthTreeNode right = node.right = new MaxDepthTreeNode(20);
        right.left = new MaxDepthTreeNode(15);
        right.right = new MaxDepthTreeNode(7);
        MaxDepthSolution so = new MaxDepthSolution();
        int res = so.maxDepth(node);
        System.out.println("二叉树的最大深度是"+res);
    }
}

/**
 * Definition for a binary tree node.
 */
class MaxDepthTreeNode {
    int val;
    MaxDepthTreeNode left;
    MaxDepthTreeNode right;
    MaxDepthTreeNode(int x) { val = x; }
}

///**
// * recursion method: top-down
// */
//class MaxDepthSolution{
//    int maxdepth = 0;
//    public int maxDepth(MaxDepthTreeNode root) {
//        if(root == null){
//            return 0;
//        }
//        helper(root, 0);
//        return maxdepth;
//    }
//    public void helper(MaxDepthTreeNode node, int level){
//        if(node==null){
//            maxdepth = Math.max(level, maxdepth);
//        }
//        else{
//            helper(node.left, level+1);
//            helper(node.right,level+1);
//        }
//    }
//}

/**
 * recursion method: buttom-up
 */
class MaxDepthSolution{
    public int maxDepth(MaxDepthTreeNode root) {
        if(root == null){
            return 0;
        }
        int childdepth = Math.max(helper(root.left), helper(root.right));
        return childdepth+1;
    }
    public int helper(MaxDepthTreeNode node){
        if(node==null){
            return 0;
        }
        int leftDepth = helper(node.left);
        int rightDepth = helper(node.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}