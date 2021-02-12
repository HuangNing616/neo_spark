package com.huangning.java.leetcode.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Author: Henry
 Date: 03/25/2020
 Knowledge: 1. 字符串转数字:
            1) 通过八种基本类型的包装类可以实现把字符串转换成基本类型，八种包装类都提供了静态方法xxxx.parseXxx(String str)
            eg:
             byte b = Byte.parseByte(s);
             short t = Short.parseShort(s);
             int i = Integer.parseInt(s);
             long l = Long.parseLong(s);
             Float f = Float.parseFloat(s);
             Double d = Double.parseDouble(s);
             boolean bo = Boolean.parseBoolean(s);
             char c = Character.parseCharacter(s);
             2) i=Integer.valueOf(s).intValue();
             方法1直接使用静态方法，不会产生多余的对象，但会抛出异常。方法2，Integer.valueOf(s
             ) 相当于new Integer(Integer.parseInt(s))，也会抛异常，但会多产生一个对象。
             2. 数字转字符串
             1)String.valueOf(Object)
             其中 value 为任意一种数字类型。将不用担心object是否为null值这一问题。
             当object为null时，String.valueOf（object）的值是字符串”null”，而不是null！！！在使用过程中切记要注意。
             2)、Object.toString()
             在使用时要注意，必须保证object不是null值，否则将抛出NullPointerException异常。
             3)、i + “”
             4)、（String）要转换的对象
             使用这种方法时，需要注意的是类型必须能转成String类型。因此最好用instanceof做个类型检查，
             以判断是否可以转换。否则容易抛出CalssCastException异常。此外，需特别小心的是因定义为
             Object类型的对象在转成String时语法检查并不会报错，这将可能导致潜在的错误存在。这时要格外
             小心。此外，因null值可以强制转换为任何java类类型，(String)null也是合法的。
            3. 灵活运用substring 和 length方法
            4. 字符串的split方法，如果最后一个字符是分割字符，那按照分割字符分割后得到的数组的最后一个元素就是最后一个和倒数第二个分割字符之间对应的元素
            5. 将一个数组转换为 List 用Arrays.asList，语法：1）Arrays.asList（"dd", "ddd"）2) String str==xxxx; Arrays.asList（str）
            用 asList 方法产生的 List 是固定大小的，这也就意味着任何改变其大小的操作都是不允许的
            asList 方法产生的 ArrayList 不能修改大小，是因为这个 ArrayList 并不是“货真价实”的 ArrayList ，那我们就自行创建一个真正的 ArrayList ：
             String[] myArray = { "Apple", "Banana", "Orange" };
             List<String> myList = new ArrayList<String>(Arrays.asList(myArray));
             myList.add("Guava");
            在上面这段代码中，我们 new 了一个 java.util.ArrayList ，然后再把 asList 方法的返回值作为构造器的参数传入，最后得到的 myList 自然就是可以动态扩容的了。
            6. List有get方法, 将list作为参数之后传入函数，并对其进行修改，那么原来的list也会有相应的修改

 Question:
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or
 another computer environment.
 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be
 deserialized to the original tree structure.
 Example:

 You may serialize the following tree:

       1
      / \
     2   3
        / \
       4   5

 as "[1,2,3,null,null,4,5]"
 Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to
 follow this format, so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 should be stateless.
 **/


public class SerializeDeserializeBinaryTree {
    public static void main(String []args){
        SeDeTreeNode node = new SeDeTreeNode(1);
        SeDeTreeNode left = node.left = new SeDeTreeNode(2);
        SeDeTreeNode right = node.right = new SeDeTreeNode(3);
        right.left = new SeDeTreeNode(4);
        right.right = new SeDeTreeNode(5);

        Codec so = new Codec();
        String res = so.serialize(node);
        SeDeTreeNode resnode = so.deserialize(res);
        System.out.println(res);
        System.out.println(resnode.val);
    }
}

/**
 * Definition for a binary tree node.
 */
class SeDeTreeNode {
     int val;
     SeDeTreeNode left;
     SeDeTreeNode right;
     SeDeTreeNode(int x) { val = x; }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(SeDeTreeNode root) {
        if(root == null){
            return "null,";
        }
        String ans = root.val  + ",";
        ans += serialize(root.left);
        ans += serialize(root.right);

        return ans;
    }

    public SeDeTreeNode rDesrialize(List<String> list){
        if(list.get(0).equals("null")){
            list.remove(0);
            return null;
        }
        int rootvalue = Integer.parseInt(list.remove(0));
        SeDeTreeNode root = new SeDeTreeNode(rootvalue);
        root.left = rDesrialize(list);
        root.right = rDesrialize(list);
        return root;
    }
    // Decodes your encoded data to tree.
    public SeDeTreeNode deserialize(String data) {
        // split the string
        String [] arr = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        return rDesrialize(list);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));