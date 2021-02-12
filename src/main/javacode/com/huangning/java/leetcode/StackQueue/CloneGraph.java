package com.huangning.java.leetcode.StackQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author：Henry
 * Date: 16/04/2020
 * knowledge: 1. 写完后发现dfs和bfs的代码是一摸一样，只是数据结构不同，一个是栈，一个是队列
 *            2. 记住如果写if else if 的话，程序只能选择其中的一项执行
 *

 * Question:
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 */
public class CloneGraph {
    public static void main(String []args){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneSolution so = new CloneSolution();
        Node res = so.cloneGraph(node1);

        System.out.println(res.val);

    }
}

/*
// Definition for a Node.
*/
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

// method 1 recursion dfs
class CloneSolution {
    HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        if(visited.containsKey(node)){
            return visited.get(node);
        }
        Node clone = new Node(node.val, new ArrayList<>());
        visited.put(node, clone);

        // traversal the neighbor of the node
        for (Node n : node.neighbors){
            clone.neighbors.add(cloneGraph(n));
        }

        return clone;
    }
}

//// method 2 bfs
//class CloneSolution {
//
//
//    public Node cloneGraph(Node node) {
//        if(node==null){
//            return node;
//        }
//        LinkedList<Node> queue = new LinkedList<>();
//        HashMap<Node, Node> visit = new HashMap<>();
//        Node clone = new Node(node.val, new ArrayList<>());
//        visit.put(node, clone);
//        queue.add(node);
//        while (!queue.isEmpty()) {
//            Node head = queue.poll();
//            for (Node n : head.neighbors) {
//                if (!visit.containsKey(n)) {
//                    visit.put(n, new Node(n.val, new ArrayList<>()));
//                    queue.add(n);
//                }
//                visit.get(head).neighbors.add(visit.get(n));
//            }
//        }
//        return clone;
//    }
//}