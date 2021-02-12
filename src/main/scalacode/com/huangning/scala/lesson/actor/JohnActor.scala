package com.huangning.scala.lesson.actor

import akka.actor.Actor

class JohnActor extends Actor{
  override def receive:Receive = {
    case "Hello John" =>
      Thread.sleep(100)
      val msg = "见到你很高兴"
      println(s"John: $msg")
      sender() ! msg

    case "打篮球" =>
      Thread.sleep(100)
      val msg = "哇，打篮球可以啊"
      println(s"John: $msg")
      sender() ! msg

    case _ =>
      println("什么消息都没有收到")
  }
}