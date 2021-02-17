package com.huangning.scala.lesson.`implicit`

/**
 * Author: 黄宁
 * Date: 2020-06-16
 * Target: 了解隐式转换中的隐式类
 * 1. implicit类不能定义在外面，只能定义在object和class内部
 * 2. 隐式类的构造必须只有一个参数，
 * 3. 同一个object或者class中不能出现同类型构造的隐式类。
 */

object ImplicitTrans3 {

  // 在object中创建隐式类
  implicit class Animal1(r:Dog){
    def showname(): Unit = {
      println(s"${r.name} is ")
    }
  }

  def main(args: Array[String]): Unit = {
    val dog: Dog = new Dog("puppy")
    dog.showname()
  }
}
