package com.huangning.java.leetcode.StackQueue;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Henry
 * Date: 2020 - 4 - 18
 * Question:
 *
 */
public class InOrderTraversal {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = node1.right = new TreeNode(2);
        node2.left = new TreeNode(3);
        InorderSolution so = new InorderSolution();
        List<Integer> res = so.inorderTraversal(node1);
        System.out.println(res);
    }
}


/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 * method: iterative method
 */
class ColorNode{
    public TreeNode node;
    public String color;
    public ColorNode(TreeNode node, String color){
        this.node = node;
        this.color = color;
    }
}

class InorderSolution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root==null){
            return res;
        }
        Stack<ColorNode> stack = new Stack<>();
        ColorNode c = new ColorNode(root, "white");
        stack.push(c);

        while (!stack.isEmpty()){
            ColorNode current = stack.pop();

            if(current.node == null) continue;

            if(current.color.equals("grey")){
                res.add(current.node.val);
            }
            else if(current.color.equals("white")){
                current.color = "grey";
                stack.push(new ColorNode(current.node.right, "white"));
                stack.push(current);
                stack.push(new ColorNode(current.node.left, "white"));
            }
        }
        return res;
    }
}

