package com.huangning.scala.lesson.`implicit`

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Target: 了解隐式转换中的隐式值和隐式参数
 * Note:
 * 1. 同类型参数的隐式值作用域内只能出现一次
 * 2. implicit关键字必须放在隐式参数定义的开头
 * 3. 如果方法只有一个参数且是隐式转换参数时，那么可以通过只写函数名的方式调用该函数
 * 4. 如果方法有多个参数，要实现部分参数的隐式转换必须使用柯里化这种方式, 隐式关键字出现在后面且出现一次
 */
object Lesson_ImplicitTrans {

  def Student(age: Int)(implicit name: String, weight: Int): Unit = {
    println(s"$name is a student... , age = $age, weight = $weight")
  }

  def Teacher(implicit name: String): Unit = {
    println(s"$name is a teacher")
  }

  def main(args: Array[String]): Unit = {
    implicit val n: String = "huangning"
    implicit val w: Int = 25

    Student(12)
    Teacher
  }
}
