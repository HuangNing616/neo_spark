package com.huangning.scala.lesson.`implicit`

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Target: 了解隐式转换中的隐式转换函数
 * Note:
 * 隐式转换函数只与函数的参数类型和返回类型有关，与函数名称无关，
 * 所以作用域内不能有相同的参数类型和返回类型的不同名称隐式转换函数
 */
class Animal(name:String){
  def fly(): Unit ={

    // 直接调用主构造方法中的name参数
    println(s"$name can fly ....")
  }
}

class Rabbit(xname:String){
  val name: String = xname
}

object Lesson_implicitTrans2 {

  implicit def rabbitToAnimal(r:Rabbit):Animal={
    new Animal(r.name)
  }

  def main(args: Array[String]): Unit = {
    val rabbit = new Rabbit("rabbit")
    rabbit.fly()
  }
}
