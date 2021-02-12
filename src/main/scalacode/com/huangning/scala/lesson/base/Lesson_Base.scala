package com.huangning.scala.lesson.base

/**
 * Author: 黄宁
 * Date: 2020/06/26
 * Target: 掌握Scala常用的的基础知识
 */

object Lesson_Base {
  def main(args: Array[String]): Unit = {

    // var是变量，val是常量
    var i = 0
    val age = 30

    // 1. while/do while 循环
    do {
      println(s" 第 $i 次求婚。。。")
      i += 1
    } while (i < 10)

    while (i < 10) {
      println(s" 第 $i 次求婚。。。")
      i += 1
    }

    // 2. to/until
    // to/until叫操作符操作，因为他们可以写成方法的形式, to是左闭右闭，until是左闭右开
    val r: Seq[Int] = 1.to(10, 1)
    val r1: Seq[Int] = 1.until(10, 3)

    for (i <- 1 until 10) print(i + " ")
    for (i <- 1 to 10) print(i + " ")


    // 3. for循环
    for (i <- 1 until 10) {
      for (j <- 1 until 10) {
        if (j <= i) {
          print(s"$i * $j = " + i * j + "\t")
        }
      }
      println()
    }

    // 将上述两个for循环体合并
    for (i <- 1 until 10; j <- 1 until 10) {
      if (j <= i) {
        print(s"$i * $j = " + i * j + "\t")
      }
      if (j == i) {
        println()
      }
    }

    // for循环中可以写多个判断语句，中间用空格隔开
    for (i <- 1 until 10 if i > 5 if i % 2 == 0) println(i)
    for (i <- 1 until 10 if i > 5 & i % 2 == 0) println(i)

    // for循环有返回值
    val result: Seq[Int] = for (i <- 1 until 100 if i > 10 if i % 3 == 0) yield i

    // 4. if/else if/else
    if (age <= 20) println("age <= 20")
    else if (age > 20 && age <= 30) println("age > 20 and age <= 30")
    else println("age > 30")

    // 5. 字符串
    // 字符串前面加s, 表示后面可以直接在字符串里面写变量，引用变量的形式就是$+变量
    val str1 = "huangning"
    val str2 = "HuangNing"
    val str3 = "Tony"

    // 寻找字符串中某个元素的index
    println(s"第一个符合ascill码的字符串元素n的下标:${str1.indexOf(110)}")
    println(s"第一个符合字符串元素n的下标:" + str1.indexOf("n"))
    println(s"如果找不到对应的元素会返回:" + str1.indexOf("z"))

    // 比较两个字符串的大小
    println(s"不忽略大小写比较：${str1 == str2}")
    println(s"不忽略大小写比较：${str1.equals(str2)}")
    println(s"忽略大小写比较：${str1.equalsIgnoreCase(str2)}")

    // 返回值是数字且忽略大小写的字符串比较
    println(s"前者等于后者的结果：${str1.compareToIgnoreCase(str2)}")
    println(s"前者大于后者的结果：${str3.compareToIgnoreCase(str1)}")
    println(s"前者小于后者的结果：${str1.compareToIgnoreCase(str3)}")

    // 返回值是数字且不忽略大小写的字符串比较
    println(s"前者等于后者的结果：${str1.compareTo(str1)}")
    println(s"前者大于后者的结果：${str1.compareTo(str2)}")
    println(s"前者小于后者的结果：${str2.compareTo(str1)}")

    // 字符串的量种遍历方法
    println("第一种遍历方法")
    for (i <- str1) print(i + ",")
    println("")

    println("第二种遍历方法")
    str1.foreach((elem: Char) => print(elem + ","))
    println("")

    str1.foreach(print)
    println("")

    // 创建可变字符串对象
    val str = new StringBuilder
    str.append("abc")
    str.++=("efg")
    str ++= "efg"
    str.+=('h')
    str += 'h'
    str.append(1.0)
    str.append("===>")
    str.append(18f)
    println(s"添加元素后的字符串为：$str")
  }
}
