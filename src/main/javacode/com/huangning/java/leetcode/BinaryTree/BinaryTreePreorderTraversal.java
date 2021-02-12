package com.huangning.java.leetcode.BinaryTree;

import java.util.LinkedList;
import java.util.List;

/**
    Author: Henry
    Date: 02/15/2020
    Question: Given a binary tree, return the preorder traversal of its nodes' values
    Knowledge:“java中每个java源文件都是一个编译单元，每一个编译单元只能有一个public类，而且源文件名必须要与类名相同”这样一个规则, 为了加速编译
    Example:
    Input: [1,null,2,3]
       1
        \
         2
        /
       3
    Output: [1,2,3]

**/
public class BinaryTreePreorderTraversal {
    public static void main(String []args){

        // create a object of TreeNode
        TreeNode node = new TreeNode(1);
        TreeNode rightnode = node.right = new TreeNode(2);
        rightnode.left = new TreeNode(3);

        // create a object of Solution
        PreorderSolution so = new PreorderSolution();

        // father reference direct child object
        List<Integer> res= so.preorderTraversal(node);
        System.out.println(res);

    }
}

/**
 * Definition for a binary tree node.
 **/
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

     // create a constructor of TreeNode
     TreeNode(int x) { val = x; }
 }

/**
 * iteratively method
 * time complex: O(n)
 * space complex: O(n)
 */
class PreorderSolution {
     public List<Integer> preorderTraversal(TreeNode root) {

         // judege if the root of tree is empty
         if (root == null) {
             return new LinkedList<>();
         }

         // initialize the stack and result
         LinkedList<TreeNode> stack = new LinkedList<>();
         LinkedList<Integer> result = new LinkedList<>();

         // add root to the stack
         stack.add(root);

         while (!stack.isEmpty()) {

             // pop the top element of the stack
             TreeNode node = stack.pollLast();
             result.add(node.val);

             // push the left child and right child to the stack
             if (node.right != null) {
                 stack.add(node.right);
             }
             if (node.left != null) {
                 stack.add(node.left);
             }
         }

         return result;
     }
 }

///**
// * recursion method
// * time complex: O(n)
// * space complex: O(n)
// */
//class PreorderSolution {
//    public List<Integer> preorderTraversal(TreeNode root) {
//
//        // judege if the root of tree is empty
//        if (root == null) {
//            return new LinkedList<>();
//        }
//
//        // change root value into LinkedList
//        LinkedList<Integer> rootlist= new LinkedList<Integer>();
//        rootlist.add(root.val);
//
//        // traversal the subtree
//        List<Integer> leftlist= preorderTraversal(root.left);
//        List<Integer> rightlist= preorderTraversal(root.right);
//
//        rootlist.addAll(leftlist);
//        rootlist.addAll(rightlist);
//
//        return rootlist;
//    }
//}