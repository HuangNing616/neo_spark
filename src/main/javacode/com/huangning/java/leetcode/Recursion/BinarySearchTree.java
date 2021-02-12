package com.huangning.java.leetcode.Recursion;

/**
 *  Author: Henry
 *  Date: 03/29/2020
 *  Knowledge: 利用BST的特点寻找指定节点可以节省寻找时间，并且可以采用递归方法，但是一般的树寻找指定节点并返回该节点，就无法使用递归方法，
 *  Question:
 *  Reverse a singly linked list.
 *
 * Example:
 Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's
 value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
 For example,

 Given the tree:
      4
     / \
    2   7
   / \
  1   3

 And the value to search: 2
 You should return this subtree:

     2
    / \
   1   3
 In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.

 Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree format) as [], not null.
 */
public class BinarySearchTree {
    public static void main(String [] args){
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = node1.left = new TreeNode(2);
        node1.right = new TreeNode(7);
        node2.left = new TreeNode(1);
        node2.right = new TreeNode(3);

        BSTSolution so = new BSTSolution();
        TreeNode res = so.searchBST(node1, 2);
        System.out.println("搜索结果是"+res);
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

///**
// * iterative method
// */
//class BSTSolution {
//    public TreeNode searchBST(TreeNode root, int val) {
//        if(root==null){
//            return null;
//        }
//
//
//        // initialize a queue to store root node
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//
//        while (!queue.isEmpty()){
//            TreeNode head = queue.poll();
//            // if head.val is the object val, we should return the node else continue the loop
//            if(head.val == val){
//                return head;
//            }
//            if(head.left!=null){
//                queue.add(head.left);
//            }
//            if(head.right!=null){
//                queue.add(head.right);
//            }
//        }
//        return null;
//    }
//}


/**
 * recursive method
 * 平均时间复杂度： O(logN)，最坏时间复杂度为：O(N)
 * 空间复杂度：O(1)
 */
class BSTSolution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        while (root!=null&&root.val!=val) {
            root = (root.val>val)?root.left : root.right;
        }
        return root;
    }
}

///**
// * recursive method
// * 平均时间复杂度： O(logN)，最坏时间复杂度为：O(N)
// * 空间复杂度：平均情况下深度为 O(logN)，最坏情况下深度：为O(N)
// */
//class BSTSolution {
//    public TreeNode searchBST(TreeNode root, int val) {
//        if (root == null || root.val == val) {
//            return root;
//        }
//        return (root.val>val)?searchBST(root.left, val): searchBST(root.right, val);
//    }
//}