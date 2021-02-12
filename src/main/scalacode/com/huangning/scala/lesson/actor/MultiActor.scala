package com.huangning.scala.lesson.actor

import akka.actor.{ActorRef, ActorSystem, Props}

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Target: 实现两个通信器之间的通信
 */
object MultiActor {
  def main(args: Array[String]): Unit = {

    //创建ActorSystem对象
    val acf: ActorSystem = ActorSystem("actorfactory")
    //先创建JohnActor的引用
    val john: ActorRef = acf.actorOf(Props[JohnActor], "JohnActor")
    //再创建TonyActor的引用
    val tony: ActorRef = acf.actorOf(Props(new TonyActor(john)), "TonyActor")
    // 先向TonyActor发送第一个消息
    tony ! "Hey Tony"
  }
}