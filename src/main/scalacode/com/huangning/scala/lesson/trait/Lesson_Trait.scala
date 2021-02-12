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
trait Read{

  val readType = "Read"
  val state = "active"

  def read(): Unit = {
    println(s"Read is starting ...")
  }

  def description(): Unit = {
    println(s"该用户的状态：$state, 行为描述：$readType")
  }
}

trait Listen{

  val listenType = "Listen"
  val state = "active"

  def listen(): Unit = {
    println(s"Listen is starting ...")
  }

  def description(): Unit = {
    println(s"该用户的状态：$state, 行为描述：$listenType")
  }
}

// 判断两个学生是否相同
trait IsEqual{

  // 未实现的方法
  def isEqu(o:Any):Boolean

  // 已实现的方法
  def isNotEqu(o:Any):Boolean = !isEqu(o)
}

class Student(sname: String, sage: Int) extends Read with Listen with IsEqual{

  val name: String = sname
  private val age: Int = sage

  var gender: String = "F"
  var id: String = "0000"

  def this(sname: String, sage: Int, sid: String){
    this(sname, sage)
    this.id = sid
  }

  def this(sname: String, sgender: String, sage: Int){
    this(sname, sage)
    this.gender = sgender
  }

  def this(sname: String, sage: Int, sid: String, sgender: String){
    this(sname, sage)
    this.gender = sgender
    this.id = sid
  }

  // 年龄是私有成员变量，在外部只能通过方法进行调用
  def sayAge(): Int = {
    age
  }

  // override继承的特质中有重复的属性gender
  override val state: String = "active"

  // override继承的特质中重复的方法description
  override def description(): Unit = {
    println(s"${name}的状态：$state, 存在的行为：$listenType, $readType")
  }

  // 需要去实现在trait中没有实现的方法isEqu
  def isEqu(o:Any):Boolean = {
    o.isInstanceOf[Student] &&
      id == o.asInstanceOf[Student].id &&
      name == o.asInstanceOf[Student].name &&
      gender == o.asInstanceOf[Student].gender
  }

  def isEquAge(o:Any):Boolean = {
    o.isInstanceOf[Student] &&
      age == o.asInstanceOf[Student].age
  }
}

object Lesson_Trait {
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
