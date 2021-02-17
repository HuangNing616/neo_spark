package com.huangning.java.leetcode.binarytree;

/**
 Author: Henry
 Date: 03/19/2020
 knowledge: 1. 1个java源文件中只能有一个public class
 Question:
 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 Note: A leaf is a node with no children.
 Example:
 Given the below binary tree and sum = 22,
      5
     / \
     4   8
     /   / \
     11  13  4
     /  \      \
     7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 **/
public class PathSum {
    public static void main(String []args){
        PathSumTreeNode node = new PathSumTreeNode(5);
        PathSumTreeNode left = node.left = new PathSumTreeNode(4);
        PathSumTreeNode right = node.right = new PathSumTreeNode(8);
        PathSumTreeNode left2 = left.left = new PathSumTreeNode(11);
        left2.left = new PathSumTreeNode(7);
        left2.right = new PathSumTreeNode(2);

        int sum = 22;
        PathSumSolution so = new PathSumSolution();
        boolean ans = so.hasPathSum(node, sum);
        System.out.println(ans);
    }
}

/**
 * Definition for a binary tree node.
 */
class PathSumTreeNode {
    int val;
    PathSumTreeNode left;
    PathSumTreeNode right;
    PathSumTreeNode(int x) { val = x; }
}

class PathSumSolution {
    int res;
    public boolean hasPathSum(PathSumTreeNode root, int sum) {
        if(root==null){
            return false;
        }
        res = sum;
        return helper(root, root.val);
    }
    public boolean helper(PathSumTreeNode node, int depth) {
        // judge if node is leaf
        if (node.left == null && node.right == null) {
            if (depth == res) {
                return true;
            }
            return false;
        }
        boolean localres = false;
        if (node.left != null) {
            localres = helper(node.left, depth + node.left.val);
        }
        if (node.right != null) {
            localres = helper(node.right, depth + node.right.val) || localres;
        }
        return localres;
    }
}