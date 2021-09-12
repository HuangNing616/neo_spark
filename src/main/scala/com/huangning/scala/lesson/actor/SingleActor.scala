package com.huangning.scala.lesson.actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Target: 了解Actor
 * Note:
 * 1. Actor模型的基本特征就是消息传递
 * 2. 消息一旦发送成功，不能修改
 * 3. 消息发送是异步非阻塞的
 */
class SingleActor extends Actor {
  def receive: Receive = {
    case "hello" => println("您好！")
    case _ => println("您是？")
  }
}

object SingleActor{
  def main(args: Array[String]): Unit = {
    // 创建通信器并发送消息
    val actor: ActorRef = ActorSystem("test").actorOf(Props[SingleActor], name = "helloActor")
    actor ! "hello"
    actor ! "good morning"
  }
}
