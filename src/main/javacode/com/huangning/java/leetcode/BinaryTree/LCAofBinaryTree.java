package com.huangning.java.leetcode.BinaryTree;


/**
 Author: Henry
 Date: 03/22/2020
 Knowledge: 1. 简单的判断语句可以使用三目运算符(): ?
            2. 判断三个boolean中是否至少有两个为true，可以将其转化成数值来进行判断
            3. LCA特有的性质就是，它的左子树，右子树和当前节点中有两个返回true
 Question:
 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

 Example 1:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 Output: 3
 Explanation: The LCA of nodes 5 and 1 is 3.

 Example 2:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 Output: 5
 Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

 Note:
 All of the nodes' values will be unique.
 p and q are different and both values will exist in the binary tree.
 **/
public class LCAofBinaryTree {
    public static void main(String []args){
        LCATreeNode node = new LCATreeNode(3);
        LCATreeNode left = node.left = new LCATreeNode(5);
        LCATreeNode right = node.right = new LCATreeNode(6);
        left.left = new LCATreeNode(6);
        left.right = new LCATreeNode(2);

        LCATreeNode p = new LCATreeNode(5);
        LCATreeNode q = new LCATreeNode(6);

        LCASolution so = new LCASolution();
        LCATreeNode res = so.lowestCommonAncestor(node, p, q);
        System.out.println(res.val);
    }
}


/**
 * Definition for a binary tree node.
 * }
 */

class LCATreeNode {
    int val;
    LCATreeNode left;
    LCATreeNode right;

    LCATreeNode(int x) {
        val = x;
    }
}

class LCASolution {

    // Variable to store LCA node.
    LCATreeNode ans = null;

    public LCATreeNode lowestCommonAncestor(LCATreeNode root, LCATreeNode p, LCATreeNode q) {
        if (root == null) {
            return null;
        }
        this.recursion(root, p, q);
        return this.ans;
    }

    public boolean recursion(LCATreeNode node, LCATreeNode p, LCATreeNode q) {
        if(node==null){
            return false;
        }

        // check if current node is betweent p and q
        int mid = (node.val == p.val || node.val == q.val) ? 1 : 0;

        // left recursion, if left recusion return true, set left =1 else 0
        int left = this.recursion(node.left, p, q) ? 1 : 0;

        // right recursion
        int right = this.recursion(node.right, p, q) ? 1 : 0;

        // if any two flag node of the left, mid , right is over 2 set current node to LCA
        if (mid + left + right >= 2) {
            this.ans = node;
        }

        return (mid + left + right) > 0;
    }
}