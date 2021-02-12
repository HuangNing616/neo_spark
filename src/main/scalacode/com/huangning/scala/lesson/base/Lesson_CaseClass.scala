package com.huangning.scala.lesson.base

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Targe: 了解样例类基本使用方法
 */
case class Person1(var name:String, age:Int)

class Person2(var name:String, val age:Int)

object Lesson_CaseClass {
  def main(args: Array[String]): Unit = {
    val p1: Person1 = Person1("zhangsan", 18)
    val p2: Person1 = Person1("lisi", 23)
    val p3: Person1 = Person1("wangwu", 17)
    println(s"样例类中的构造参数默认是val，通过构造参数实现的setter方法获取age:${p1.age}")

    val list: List[Person1] = List[Person1](p1, p2, p3)
    println("======================================")
    println("将样例类中Person1的指定成员挑选出来")

    // foreach中的match用到了equal，而样例类重写了equals（即比较内容而不是地址）
    list.foreach { elem => {
      elem match {
        case Person1("zhangsan", 18) => println("找到了zhangsan的信息")
        case Person1("lisi", 23) => println("找到了lisi的信息")
        case _ => println("no mathch...")
      }
      }
    }

    println("============上述match的简化版本===========")
    list.foreach {
        case Person1("zhangsan", 18) => println("找到了zhangsan的信息")
        case Person1("lisi", 23) => println("找到了lisi的信息")
        case _ => println("no mathch...")
    }
    println("========================================")

    val p = new Person2("zhangsan", 18)
    println("普通类中调用构造参数的方式只能是将构造参数显式的写成val/var")
    println("使用构造参数的getter方法获取name:" +  p.name)
    p.name = "zhangsanModify"
    println("使用var构造参数的setter方法修改name:" + p.name)

    println("重写了equals方法的样例类：" + p1.equals(Person1("zhangsan", 18)))
    println("未重写equals方法的普通类：" + p.equals(new Person2("zhangsan", 18)))

    println("重写了toString方法的样例类：" + p1.toString)
    println("未重写toString方法的普通类：" + p.toString)

  }
}
