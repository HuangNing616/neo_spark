package com.huangning.scala.lesson.`trait`

// 判断两个学生是否相同
trait IsEqual{

  // 未实现的方法
  def isEqu(o:Any):Boolean

  // 已实现的方法
  def isNotEqu(o:Any):Boolean = !isEqu(o)
}
