package com.huangning.scala.lesson.base

/**
 * Author: 黄宁
 * Date: 2020/06/18
 * Target: 学习Scala的class和object
 */

// 如果pname前面写val/var，就相当于公有的，在object外部可以用，而不加val/var就相当于私有的
class ObjectClass(pname:String, page: Int){

  // 私有成员变量在class外部不能访问
  private val name = pname
  var age: Int = page
  var gender = 'M'
  var id = "0000"

  // 构造方法的重载，也叫辅助构造器(Auxiliary Constructor)，每个辅助构造器都必须以一个对先前已定义的其他辅助构造器或主构造器的调用开始
  def this(yname: String, yage: Int, ygender: Char){  //辅助构造器1
    this(yname, yage)                                 //调用主构造器
    this.gender = ygender
  }

  def this(yname: String, yage: Int, yid: String){  //辅助构造器2
    this(yname, yage)                               //调用主构造器
    this.id = yid
  }

  def this(yname: String, yage: Int, ygender: Char, yid: String){  //辅助构造器3
    this(yname, yage, ygender)                      //调用辅助构造器2
    this.id = yid
  }

  // 类的私有成员变量在外部只能通过方法来调用
  def sayName(): String ={
    name
  }

  def sayObjectName(): String = {
    ObjectClass.name
  }

  println("======= 当Person类的对象创建的时候方法以外（除了构造方法）的语句都执行 =======")

}

object ObjectClass {

  val name = "wangwu"
  println("++++ object中的所有语句都会被加载 +++++++")

  def apply(i:Int): Unit ={
    println("score is " + i)
  }

  def apply(i:Int, j: Int): Unit ={
    println("score is " + i + " the height is " + j)
  }

  def main(args: Array[String]): Unit = {

    val p = new ObjectClass("zhangsan", 20)
    val p1 = new ObjectClass("diaochan", 18, 'F')
    val p2 = new ObjectClass("houyi", 19, 'F', "0001")
    println("Person的第一位成员姓名：" + p.sayName() + " 性别：" + p.gender + " 年龄：" + p.age)
    println("Person的第二位成员姓名：" + p1.sayName() + " 性别：" + p1.gender+ " 年龄：" + p1.age )
    println("Person的第三位成员姓名：" + p2.sayName() + " 性别：" + p1.gender+ " 年龄：" + p2.age )


    // 修改类中成员变量的值
    p.age = 2
    println(p.sayName() + "的年龄被修改成" + p.age)

    // 直接调用内部成员变量
    println(name)
    // Person对象调用object Lesson_Base 中成员变量
    println("Lesson_Base 中成员变量的name：" + p.sayObjectName())

    // object 不可以传参数，如果非要传参数，就会调用相应参数长度的apply方法
    ObjectClass(100)
    ObjectClass(1000, 200)
  }
}
