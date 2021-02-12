package com.huangning.java.leetcode.BinaryTree;


import java.util.LinkedList;
import java.util.Queue;

/**
 Author: Henry
 Date: 03/17/2020
 knowledge:1. 如果树的根为空，那么默认树是镜像对称的
           2. 对于镜像问题，可以将根节点当成两个相同的孩子节点来进行第一层递归
           3. 对于1句话的if语句，可以不用加花括号
 Question: Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).


 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

       1
      / \
     2   2
    / \ / \
   3  4 4  3

 But the following [1,2,2,null,3,null,3] is not:

          1
         / \
        2   2
         \   \
         3    3

 Note:
 Bonus points if you could solve it both recursively and iteratively.
 **/
public class SymmetricTree {
    public static void main(String []args){

        // create a object of LevelorderTreeNode
        SymmetricTreeNode node = new SymmetricTreeNode(3);
        SymmetricTreeNode left = node.left = new SymmetricTreeNode(9);
        SymmetricTreeNode right = node.right = new SymmetricTreeNode(9);
        right.left = left.right =  new SymmetricTreeNode(15);
        right.right = left.left = new SymmetricTreeNode(7);
        SymmetricSolution so = new SymmetricSolution();
        boolean res = so.isSymmetric(node);
        System.out.println("二叉树是否为镜像"+res);
    }
}

/**
 * Definition for a binary tree node.
 */
class SymmetricTreeNode {
    int val;
    SymmetricTreeNode left;
    SymmetricTreeNode right;
    SymmetricTreeNode(int x) { val = x; }
}

/**
 * official answer
 */
class SymmetricSolution{
    public boolean isSymmetric(SymmetricTreeNode root) {

        Queue<SymmetricTreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while(!queue.isEmpty()) {

            SymmetricTreeNode left = queue.poll();
            SymmetricTreeNode right = queue.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val!=right.val) return false;
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);

            }

        return true;
    }
}

///**
// * iterative method
// */
//class SymmetricSolution{
//    public boolean isSymmetric(SymmetricTreeNode root) {
//        if(root == null){
//            return true;
//        }
//        Queue<SymmetricTreeNode> queue = new LinkedList<>();
//        queue.add(root.left);
//        queue.add(root.right);
//        while(!queue.isEmpty()) {
//
//            SymmetricTreeNode left = queue.poll();
//            SymmetricTreeNode right = queue.poll();
//            if (left == null && right == null) {
//                continue;
//            } else if (left == null || right == null) {
//                return false;
//            }
//            else{
//                if(left.val == right.val){
//                    queue.add(left.left);
//                    queue.add(right.right);
//                    queue.add(left.right);
//                    queue.add(right.left);
//                }
//                else{
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//}

///**
// * recursive method
// */
//class SymmetricSolution{
//    public boolean isSymmetric(SymmetricTreeNode root) {
//        return helper(root, root);
//    }
//    public boolean helper(SymmetricTreeNode left, SymmetricTreeNode right){
//        if(left==null && right==null){
//            return true;
//        }
//        else if(left==null|| right==null){
//            return false;
//        }
//        return left.val==right.val && helper(left.left, right.right) && helper(left.right, right.left);
//    }
//}