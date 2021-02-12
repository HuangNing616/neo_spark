package com.huangning.scala.lesson.actor

import akka.actor.{Actor, ActorRef}

import scala.io.StdIn

class TonyActor(actorRef: ActorRef) extends Actor {

  // 首先发送消息的actor需要拿到第二个actor的引用
  val johnRef: ActorRef = actorRef

  override def receive: Receive = {

    case "Hey Tony" =>
      println("Tony: 哇! 你竟然知道我的名字")

      //发给自己
      self ! "我心里好紧张"

    case "我心里好紧张" =>
      johnRef ! "Hello John"

    case "见到你很高兴" =>
      println("Tony: 我们下午要不要做点有趣的事情?")
      Thread.sleep(1000)
      //给JohnActor发出消息
      println("请作者提出有趣的事情:")
      val msg: String = StdIn.readLine()
      println(s"Tony: $msg")
      johnRef ! msg

    case _ =>
      println("对话结束... ...")
  }
}