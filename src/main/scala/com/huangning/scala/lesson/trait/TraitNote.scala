package com.huangning.scala.lesson.`trait`

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Target: 了解trait的基本语法
 * Note:
 * 1. trait不能传参
 * 2. trait就是java中抽象类和借口的结合体，因为trait中可以用方法体的实现也可以有不实现的相当于java的抽象类，可以多继承借口相当于java的借口
 * 3. 一个类继承多个trait的时候，第一个关键字使用extends，之后使用with (ps:trait之间可以进行多继承)；
 *    类继承的多个trait中有同名的方法和属性的时候, 必须要在该类中通过"override"重写同名的方法
 * 4. trait中可以有方法体的实现，也可以有方法体的不实现；
 *    类继承trait，就要实现trait中没有实现的方法
 */

object TraitNote {
  def main(args: Array[String]): Unit = {
    val h = new Student("huangning", 25)
    val n = new Student("Tony", 25, "0011", "F")
    println("Student的第一位成员姓名：" + h.name + " 性别：" + h.gender + " 年龄：" + h.sayAge() + " id：" + h.id)
    println("Student的第二位成员姓名：" + n.name + " 性别：" + n.gender + " 年龄：" + n.sayAge() + " id:" + n.id)

    h.read()
    h.listen()
    h.description()

    val res: Boolean = h.isEqu(n)
    println(s"${h.name}和${n.name}相同么：$res")
    println(s"${h.name}和${n.name}的年龄相同么：${h.isEquAge(n)}")
  }
}
