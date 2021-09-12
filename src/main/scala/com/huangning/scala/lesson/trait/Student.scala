package com.huangning.scala.lesson.`trait`

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