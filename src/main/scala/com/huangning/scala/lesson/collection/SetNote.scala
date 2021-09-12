package com.huangning.scala.lesson.collection

import scala.collection.mutable

/**
 * Author: 黄宁
 * Date:2020/06/26
 * Target：了解Set的基本用法
 */
object SetNote {
  def main(args: Array[String]): Unit = {

    // 创建Set（Set会自动去重）
    val set1: Set[Int] = Set[Int](1, 2, 3, 4, 1)
    val set2: Set[Int] = Set[Int](3, 4, 5, 6, 7)

    // 两种遍历方式
    set1.foreach(print)
    println()
    for (elem <- set2) print(elem + ",")
    println()

    /**
     * Set的方法：
     * 0. 并集: ++/|/union
     * 1. 交集：intersect/&/
     * 2. 差集: diff/&~/--
     * 3. 子集: subsetOf, 判断当前集合是否是传入参数的子集
     * 4. 最大: max
     * 5. 最小: min
     * 6. 转成List: toList
     * 7. 转成Array: toArray
     * 8. 转成字符串: mkString(“~”)
     * 9. filter
     */
    val res00: Set[Int] = set1.union(set2)
    val res01: Set[Int] = set1 ++ set2
    val res02: Set[Int] = set1 | set2
    val res10: Set[Int] = set1.intersect(set2)
    val res11: Set[Int] = set1 & set2
    val res20: Set[Int] = set1.diff(set2)
    val res21: Set[Int] = set1 &~ set2
    val res22: Set[Int] = set1 -- set2
    val res3: Boolean = set1.subsetOf(set2)
    val res4: Int = set1.max
    val res5: Int = set1.min
    val res6: List[Int] = set1.toList
    val res7: Array[Int] = set1.toArray
    val res8: String = set1.mkString("~")
    val res9: Set[Int] = set1.filter(elem => {
      elem > 2
    })

    // 创建可变长Set
    val muSet: mutable.Set[Int] = mutable.Set[Int](10, 20, 30)
    muSet.+=(100) // 追加一个元素
    muSet.+=(100, 200, 300) // 追加多个元素
    muSet.foreach(println)

  }
}
