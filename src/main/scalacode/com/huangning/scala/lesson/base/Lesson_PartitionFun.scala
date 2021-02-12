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
object Lesson_PartitionFun {

  // 定义偏函数
  def MyTest: PartialFunction[String, Int] = {
    case "abc" => 2
    case "a" => 1
    case _ => 200
  }

  def main(args: Array[String]): Unit = {
    val result: Int = MyTest("abc")
    println(result)
  }
}
