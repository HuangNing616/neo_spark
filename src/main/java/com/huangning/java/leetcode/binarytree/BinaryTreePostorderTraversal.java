package com.huangning.java.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 Author: Henry
 Date: 03/15/2020
 Question: Given a binary tree, return the postorder traversal of its nodes' values.
 Knowledge:"java中方法不同于其他语言的函数，方式是属于某个类或者对象的，所以方法不能想函数一样独立执行，需要类或者方法作为调用者，格式就
            是类.方法/对象.方法，但是同一个类不同方法不是可以相互调用吗？本质其实是如果被调用方法是非静态方法/普通方法，则默认使用this作
            为调用者，那么默认是this.方法，如果被调用的是静态方法，那么默认使用类作为调用者，也就是说表面上看起来某些方法可以被独立执行，
            但实际上还是使用this或类来作为调用者
            linkedlist 特有的方法：pollLast, addFirst
 tip: 后序遍历的时候一定要考虑树的根为空的特殊情况，以免会出现列表中添加空值的状况

 Example:
 Input: [1,null,2,3]
 1
  \
   2
  /
 3
 Output: [3,2,1]
 **/
public class BinaryTreePostorderTraversal {
    public static void main(String []arg){

        // create a object of PostorderTreeNode
        PostorderTreeNode node = new PostorderTreeNode(1);
        PostorderTreeNode right = node.right = new PostorderTreeNode(2);
        right.left = new PostorderTreeNode(3);
        PostorderSolution so = new PostorderSolution();
        List<Integer> res = so.postorderTraversal(node);
        System.out.println(res);

    }
}

/**
 * Definition for a binary tree node.
 */
class PostorderTreeNode {
    int val;
    PostorderTreeNode left;
    PostorderTreeNode right;
    PostorderTreeNode(int x) { val = x; }
}

/**
 * iteratively method
 * time complex: O(n)
 * space complex: O(n)
 */
class PostorderSolution {
    public List<Integer> postorderTraversal(PostorderTreeNode root) {

        LinkedList<PostorderTreeNode> stack = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        if(root == null){
            return list;
        }

        stack.add(root);
        while(!stack.isEmpty()){
            // pop the last element of the stack
            PostorderTreeNode curr = stack.pollLast();

            list.addFirst(curr.val);
            if(curr.left != null){
                stack.add(curr.left);
            }
            if(curr.right != null){
                stack.add(curr.right);
            }
        }
        return list;
    }
}

///**
// * recursion method
// * time complex: O(n)
// * space complex: O(logn)
// */
//class PostorderSolution {
//    public List<Integer> postorderTraversal(PostorderTreeNode root) {
//
//        List<Integer> list = new ArrayList<>();
//        recursion(root, list);
//        return list;
//    }
//    public void recursion(PostorderTreeNode root, List<Integer> list){
//        // judge if the root is null
//        if(root != null){
//            // get left subtree result
//            if(root.left != null){
//                recursion(root.left, list);
//            }
//            if(root.right != null) {
//                recursion(root.right, list);
//            }
//
//            // combine the result of lefttree, root and right tree
//            list.add(root.val);
//        }
//    }
//}