package com.huangning.scala.lesson.base

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Target: 了解match（模式匹配）的基本语法
 * Match 模式匹配
 * 1. match不仅可以匹配值还可以匹配类型
 * 2. 从上往下匹配，匹配上之后自动终止
 * 3. 模式匹配外部的{...}可以省略掉
 * 4. case _ 可以匹配任何情况（相当于java的default)，一般用在都匹配不上的时候，一定放在最后！！！
 * 5. 匹配过程中会有数值的转换，比如1.0转换成1来和相应项进行匹配
 */
object Lesson_Match {

  def main(args: Array[String]): Unit = {
    val tp: (Int, Char, Double, String, Char, Boolean, Null) = (1, 'd', 2.0, "abc", 'c', true, null)
    val iterator: Iterator[Any] = tp.productIterator
    iterator.foreach(MatchTest)

    val v = 1.0
    val str: String = v match {
      case i1 if v >= 2 && v <= 5 => s"$i1 的范围在 2～5"
      case i2 if 3 to 10 contains i2 => s"$i2 的范围在 3～10"
      case 1 => "value is 1"
      case _ => "default"
    }
    println(str)

  }

  // 判断元祖中的每个数据类型
  def MatchTest(o: Any): Unit =
    o match {
      case i: Int => println(s"type is Int, value = $i")
      case 1 => println("value is 1")
      case d: Double => println(s"type is Double, value = $d")
      case s: String => println(s"type is String, value = $s")
      case 'd' => println(s"value is d")
      case c: Char => println(s"type is Char, value = $c")
      case b: Boolean => println(s"type is Boolean, value = $b")
      case _ => println("no match...")
    }
}
