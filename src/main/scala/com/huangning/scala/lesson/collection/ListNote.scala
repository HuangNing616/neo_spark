package com.huangning.scala.lesson.collection

import scala.collection.mutable.ListBuffer

/**
 * Author: 黄宁
 * Date: 2020/06/26
 * Target: 了解List（列表）的基本用法
 * Note:
 * 1. List如果不写泛型，有时候编译器推断不出来类型，并显示nothing
 * 2. 写泛型的好处就是如果写错成其他类型，会有报错提示
 */
object ListNote {
  def main(args: Array[String]): Unit = {

    // 创建List, Nil是长度为0的list
    val l1: List[Int] = List[Int](1, 2, 3, 4, 5)
    val l2: List[String] = List[String]("hello scala", "hello spark", "hello java", "hn", "tony")

    // 两种遍历方式
    for (elem <- l1) println(elem)
    l1.foreach(println)

    /**
     * List的方法：
     * 1. filter: 将返回是true的元素保留下来
     * 2. count: 计算符合条件的元素个数
     * 3. map: 一一映射
     * 4. flatMap: 先map，再flat
     */
    val res1: List[String] = l2.filter(s => {
      "hello scala".equals(s)
    })

    val res2: Int = l2.count(s => {
      s.length >= 4
    })

    val res3: List[Array[String]] = l2.map(elem => {
      elem.split(" ")
    })

    val res4: List[String] = l2.flatMap(elem => {
      elem.split(" ")
    })

    // 创建可变长的List
    val list: ListBuffer[Int] = ListBuffer[Int](1, 2, 3, 4)
    list.append(4, 5, 6) // 后向追加多个元素
    list.+=(1000, 2000) // 后向追加多个元素
    list.+=:(100) // 前向追加多个元素

  }
}
