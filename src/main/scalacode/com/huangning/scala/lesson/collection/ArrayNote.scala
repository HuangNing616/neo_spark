package com.huangning.scala.lesson.collection

import scala.collection.mutable.ArrayBuffer

/**
 * Author: 黄宁
 * Date: 2020/06/26
 * Target: 掌握Array（数组）的基本用法
 */
object ArrayNote {
  def main(args: Array[String]): Unit = {

    // 创建Array（Array类）
    val arrInt = new Array[Int](3) // 整型数组默认都是0
    val arrFloat = new Array[Float](3) // Float数组默认都是0.0
    val arrString = new Array[String](3) // String数组默认都是null
    val arrBoolean = new Array[Boolean](3) // Boolean数组默认都是false

    // 创建Array（Array的Object）
    val arrIntObj: Array[Int] = Array[Int](1, 2, 3, 4, 5)
    val arrStringObj: Array[String] = Array[String]("a", "b", "c", "d")

    // 数组的赋值/修改
    arrInt(0) = 100
    arrInt(1) = 200
    arrInt(2) = 300

    // 两种遍历方式
    arrInt.foreach(println)
    for (elem <- arrInt) println(elem)

    // 创建可变长数组
    val arr1: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, 3)
    arr1.+=(4, 3) // 向后追加，可添加多个元素
    arr1.+=:(100) // 向前追加
    arr1.append(7, 8, 9) // 向后追加，可添加多个元素

    // Array的fill：创建长度指定且重复某一元素的数组（和柯里化类似）
    val strFill: Array[String] = Array.fill(5)("hello")

    // Array的concat：将两个数组合并
    val constr: Array[String] = Array.concat(strFill, arrStringObj)
    constr.foreach(println)

    // 创建二维数组
    val array = new Array[Array[Int]](3)
    array(0) = Array[Int](1, 2, 3, 4)
    array(1) = Array[Int](4, 5, 6)
    array(2) = Array[Int](7, 8, 9)

    // 二维数组的两种遍历方法
    for (arr <- array; elem <- arr) print(elem)
    println()
    array.foreach(arr => {
      arr.foreach(print)
    })
  }
}
