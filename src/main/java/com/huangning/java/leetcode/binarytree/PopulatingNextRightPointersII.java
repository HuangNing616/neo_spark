package com.huangning.java.leetcode.binarytree;

/**
 Author: Henry
 Date: 03/20/2020
 Knowledge: 解决该题目的两种方法：
            1. 利用层序遍历的思想，给当前层设置cur指针，给next层设置prev指针, 双指针法给next层填充next指针
            2. 利用层序遍历的思想，直接给当前层填充next指针
 Question:

 Given a binary tree
 struct Node {
 int val;
 Node *left;
 Node *right;
 Node *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 Initially, all next pointers are set to NULL.
 Follow up:
 You may only use constant extra space.
 Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 **/
public class PopulatingNextRightPointersII {
    public static void main(String []args){
        NodeII node = new NodeII(2);
        node.left = new NodeII(3);
        node.right = new NodeII(3);
        PopulatingNextRightPointersSolutionII so = new PopulatingNextRightPointersSolutionII();
        NodeII res = so.connect(node);
        System.out.println(res.val);
    }
}

/**
Definition for a Node.
*/
class NodeII {
    public int val;
    public NodeII left;
    public NodeII right;
    public NodeII next;

    public NodeII() {}

    public NodeII(int _val) {
        val = _val;
    }

    public NodeII(int _val, NodeII _left, NodeII _right, NodeII _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

///**
// * iterative method : 通过层序遍历的思想，分别给每一层的所有节点填充next pointer
// */
//class PopulatingNextRightPointersSolutionII {
//    public NodeII connect(NodeII root) {
//        //check the special situation
//        if(root==null){
//            return root;
//        }
//
//        // initialize a queue to store all the node of the current level
//        Queue<NodeII> queue = new LinkedList<>();
//
//        // because root is in the first level , we don't set next pointer to the root
//        queue.add(root);
//
//        // outer while loop which traversal each level of the tree
//        while (!queue.isEmpty()){
//
//            // get the size of the current level
//            int size = queue.size();
//            for(int i=0; i<size; i++){
//
//                //get the head of the queue
//                NodeII node = queue.poll();
//
//                // populating the next pointer
//                if(i<size-1){
//                    node.next = queue.peek();
//                }
//                // add the child node to the next level
//                if(node.left!=null){
//                    queue.add(node.left);
//                }
//                if(node.right!=null){
//                    queue.add(node.right);
//                }
//            }
//        }
//        return root;
//    }
//}

/**
 * iterative method : 利用层序遍历的思想，给每一层的所有孩子节点填充next pointer, 其中填充的时候要注意有两种next pointer，
 *                    分别为来自于同一个父节点的孩子节点，以及来自于不同父亲节点的孩子节点
 *                    space complexity O(1)
 */
class PopulatingNextRightPointersSolutionII {

    NodeII leftmost, prev;

    public void childProcess(NodeII childNode){

        // If the "prev" pointer is alread set i.e. if we
        // already found atleast one node on the next level,
        // setup its next pointer
        if(childNode != null){
            if(prev != null){
                prev.next = childNode;
            }

            else{
                // Else it means this child node is the first node
                // we have encountered on the next level, so, we
                // set the leftmost pointer
                this.leftmost = childNode;
            }

            // update prev pointer
            this.prev = childNode;
        }

    }
    public NodeII connect(NodeII root) {
        //check the special situation
        if(root==null){
            return root;
        }

        this.leftmost = root;
        // outer while loop which traversal each level of the tree
        while (this.leftmost!=null){

            // define current level node
            NodeII curr = this.leftmost;
            // the purpose of changing  leftmost to  null  is find next level leftmost
            this.leftmost = null;

            // while it means that we traversal all current level node
            while (curr!=null){
                childProcess(curr.left);
                childProcess(curr.right);
                curr = curr.next;
            }

            // initialize the prev pointer
            prev = null;
        }
        return root;
    }
}
