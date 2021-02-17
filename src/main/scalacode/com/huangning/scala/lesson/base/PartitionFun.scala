package com.huangning.scala.lesson.base

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Target: 了解PartialFunction（偏函数）的基本语法
 * Note:
 * 1. 偏函数中case后面的值类型必须和传入的类型一致
 * 2. 偏函数只能匹配一个值，匹配上了返回某个值
 * 3. PartialFunction[A, B] A是匹配的类型，B是匹配上返回的类型
 */
object PartitionFun {

  def main(args: Array[String]): Unit = {

    // 偏函数调用
    val result: Int = MyTest("abc")
    println(result)
  }

  def MyTest: PartialFunction[String, Int] = {
    case "abc" => 2
    case "a" => 1
    case _ => 200
  }
}
