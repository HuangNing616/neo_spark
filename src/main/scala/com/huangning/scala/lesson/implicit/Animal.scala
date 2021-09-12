package com.huangning.scala.lesson.`implicit`

class Animal(name:String){
  def fly(): Unit ={

    // 直接调用主构造方法中的name参数
    println(s"$name can fly ....")
  }
}