package com.huangning.scala.lesson.`trait`

trait Listen{

  val listenType: String = "Listen"
  val state: String = "active"

  def listen(): Unit = {
    println(s"Listen is starting ...")
  }

  def description(): Unit = {
    println(s"该用户的状态：$state, 行为描述：$listenType")
  }
}