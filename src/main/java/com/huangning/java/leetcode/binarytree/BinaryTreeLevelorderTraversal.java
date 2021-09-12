package com.huangning.java.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 Author: Henry
 Date: 03/16/2020
 Question:  Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 knowledge: 1. poll/pollfrist/polllast  获取并删除 （如果是空列表，返回null）
            2. peek/peekfirst/peeklast  获取
            3. offer/offerFirst/offerLast 插入
            4. remove/removeFirst/removeLast 获取并删除  （如果是空列表，直接报错）
            5. levelorder is implemented through the thought of DFS
 For example:
 Given binary tree [3,9,20,null,null,15,7],
  3
 / \
 9  20
 /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]
 time complex: O(n)
 space complex: O(n)
 **/
public class BinaryTreeLevelorderTraversal {
    public static void main(String []arg){

        // create a object of LevelorderTreeNode
        LevelorderTreeNode node = new LevelorderTreeNode(3);
        LevelorderTreeNode left = node.left = new LevelorderTreeNode(9);
        LevelorderTreeNode right = node.right = new LevelorderTreeNode(20);
        LevelorderTreeNode subleft = right.left = new LevelorderTreeNode(15);
        LevelorderTreeNode subright = right.right = new LevelorderTreeNode(7);

        LevelorderSolution so = new LevelorderSolution();
        List<List<Integer>> res = so.levelorderTraversal(node);
        System.out.println(res);

        // test.sh the difference between pollFirst and poll
        LinkedList<Integer> test = new LinkedList<>();
        test.add(1);
        test.add(2);
        test.add(4);
        System.out.println(test.peek());
        test.offerFirst(100);
        System.out.println(test.poll());
        System.out.println(test.pollFirst());
        System.out.println(test.pollLast());

    }
}

/**
 * Definition for a binary tree node.
 */
class LevelorderTreeNode {
    int val;
    LevelorderTreeNode left;
    LevelorderTreeNode right;
    LevelorderTreeNode(int x) { val = x; }
}

/**
 * recursion method
 * time complex: O(n)
 * space complex: O(n)
 * use the thought of dfs to achieve the bfs
 */
class LevelorderSolution {
    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> levelorderTraversal(LevelorderTreeNode root) {
        if(root == null){
            return result;
        }
        helper(root, 0);
        return result;
    }

    public void helper(LevelorderTreeNode node, int level){
        if(level == result.size()){
            result.add(new LinkedList<Integer>());
        }
        result.get(level).add(node.val);
        if(node.left!=null){
            helper(node.left, level+1);
        }
        if(node.right!=null){
            helper(node.right, level+1);
        }
    }
}


///**
// * iteratively method
// * time complex: O(n)
// * space complex: O(n)
// */
//class LevelorderSolution {
//    public List<List<Integer>> levelorderTraversal(LevelorderTreeNode root) {
//
//        LinkedList<LevelorderTreeNode> queue = new LinkedList<>();
//        if(root == null){
//            return new LinkedList<>();
//        }
//
//        queue.add(root);
//
//        List<List<Integer>> reslist = new LinkedList<>();
//
//        while(!queue.isEmpty()){
//
//            // get the lenght of stack
//            int length = queue.size();
//
//            //create a list to store the current level value
//            LinkedList<Integer> curlist = new LinkedList<>();
//
//            for(int i=0; i<length; i++){
//
//                // Retrieves and removes the first element of this list, or returns null if this list is empty.
//                LevelorderTreeNode curr = queue.pollFirst();
//
//                // add the current node value to the current level list
//                curlist.add(curr.val);
//                // judge if current node has child node
//                if(curr.left != null){
//                    queue.add(curr.left);
//                }
//
//                if(curr.right != null){
//                    queue.add(curr.right);
//                }
//            }
//
//            // add current level result to the result list
//            reslist.add(curlist);
//        }
//        return reslist;
//    }
//}

