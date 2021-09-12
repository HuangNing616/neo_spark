package com.huangning.scala.lesson.base

/**
 * Author: 黄宁
 * Date: 2021/02/17
 * Func: Scala基础语法
 */
object Base {
  def main(args: Array[String]): Unit = {

    /** 变量 */
    var c1: Int = 0
    var c2: Int = 0

    /** 常量 */
    val age: Int = 27

    /** while/do while循环 */
    do {
      println(s" 第 $c1 次求婚...")
      c1 += 1
    } while (c1 < 5)

    while (c2 < 5) {
      println(s" 第 $c2 次离婚...")
      c2 += 1
    }

    /**
     * to/until
     * 称为操作符操作，因为可以写成方法的形式
     * to是左闭右闭，until是左闭右开
     */
    val r1: Seq[Int] = 1.to(5, 1)
    val r2: Seq[Int] = 1.until(5, 2)
    println("to生成的序列:", r1)
    println("until生成的序列:", r2)
    for (i <- 1 to 5) print(i + " ")
    for (j <- 1 until 5) print(j + " ")

    /** for循环 */
    for (i <- 1 until 5) {
      for (j <- 1 until 5)
        if (j <= i) print(s"$i * $j = " + i * j + "\t")
      println()
    }

    // 将上述两个for循环体合并
    for (i <- 1 until 10; j <- 1 until 10) {
      if (j <= i) print(s"$i * $j = " + i * j + "\t")
      if (j == i) println()
    }

    // for循环中可以写多个判断语句，中间用空格隔开
    for (i <- 1 until 10 if i > 5 if i % 2 == 0) println(i)
    for (i <- 1 until 10 if i > 5 & i % 2 == 0) println(i)

    // for循环有返回值
    val res: Seq[Int] = for (i <- 1 until 10 if i < 10 if i % 3 == 0) yield i
    println("for循环生成的序列:", res)

    /** if/else */
    if (age <= 20) println("age <= 20")
    else if (age > 20 && age <= 30) println("age > 20 and age <= 30")
    else println("age > 30")
  }
}
