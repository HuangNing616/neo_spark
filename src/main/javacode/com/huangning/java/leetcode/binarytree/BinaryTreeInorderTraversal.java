package com.huangning.java.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 Author: Henry
 Date: 03/15/2020
 Question: Given a binary tree, return the inorder traversal of its nodes' values.
 Knowledge:"ArrayList, LinkedList, Vector, Stack 都继承于抽象类AbstractList，进而实现List借口"
 Example:
 Input: [1,null,2,3]
 1
 \
 2
 /
 3
 Output: [1,3,2]
 **/
public class BinaryTreeInorderTraversal {
    public static void main(String []arg){

        // create a object of InorderTreeNode
        InorderTreeNode node = new InorderTreeNode(1);
        InorderTreeNode right = node.right = new InorderTreeNode(2);
        right.left = new InorderTreeNode(3);
        InorderSolution so = new InorderSolution();
        List<Integer> res = so.inorderTraversal(node);
        System.out.println(res);
    }
}

/**
 * Definition for a binary tree node.
 */
class InorderTreeNode {
    int val;
    InorderTreeNode left;
    InorderTreeNode right;
    InorderTreeNode(int x) { val = x; }
}

/**
 * iteratively method
 * time complex: O(n)
 * space complex: O(n)
 */
class InorderSolution {
    public List<Integer> inorderTraversal(InorderTreeNode root) {

        Stack<InorderTreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        InorderTreeNode curr = root;
        while(curr!=null || !stack.isEmpty()){
            while (curr!=null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
        return list;
    }
}

///**
// * recursion method
// */
//class InorderSolution {
//    public List<Integer> inorderTraversal(InorderTreeNode root) {
//        // judge if the root is null
//        if(root == null){
//            return new LinkedList<>();
//        }
//
//        // get left subtree result
//        List<Integer> left = inorderTraversal(root.left);
//        List<Integer> right = inorderTraversal(root.right);
//
//        // combine the result of lefttree, root and right tree
//        left.add(root.val);
//        left.addAll(right);
//
//        return left;
//    }
//}