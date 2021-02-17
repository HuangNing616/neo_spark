package com.huangning.scala.lesson.`trait`

trait Read{

  val readType: String = "Read"
  val state: String = "active"

  def read(): Unit = {
    println(s"Read is starting ...")
  }

  def description(): Unit = {
    println(s"该用户的状态：$state, 行为描述：$readType")
  }
}