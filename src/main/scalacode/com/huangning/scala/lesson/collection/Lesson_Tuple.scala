package com.huangning.scala.lesson.collection

/**
 * Author: 黄宁
 * Date: 2020/06/16
 * Target: 了解Tuple的基本用法
 * Note:
 * 1. 元组最多支持22各元素，超过22个元素的就不叫Tuple，而是Nothing类型并且报错
 * 2. 元组可new，也可以不new，甚至可以直接写()
 * 3. 与列表类似，唯一不同的是元组可以包含不同类型的元素。
 * 4. 元组的值是通过将单个的值包含在圆括号中构成的
 * 5. tuple本身不是iterator，所以遍历的时候应该先通过productIterator获取它的iterator
 */
object Lesson_Tuple {
  def main(args: Array[String]): Unit = {

    // 创建Tuple
    val tuple1: Tuple1[String] = new Tuple1("hello")
    val tuple2: (String, Int) = new Tuple2("a", 100)
    val tuple3: (Int, Boolean, Char) = new Tuple3(1, true, 'c')
    val tuple4: (Int, Double, String, Boolean) = Tuple4(1, 3.4, "abc", false)
    val tuple6: (Int, Int, Int, Int, Int, String) = (1, 2, 3, 4, 5, "abd")
    val tuple22: (Int, Int, Int, Int, String, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int) = new Tuple22(1, 2, 3, 4, "ss", 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2)

    // 获取Tuple中的值
    val value1: String = tuple22._5

    // 元组遍历的三种方法(iterator就相当于指向某个位置的指针)
    val iterator: Iterator[Any] = tuple2.productIterator
    while (iterator.hasNext) {
      println(iterator.next())
    }
    for (item <- iterator) {
      println(item)
    }
    iterator.foreach(println)

    // 2元数组的翻转方法
    println(tuple2.swap)

    // toString方法
    println(tuple3.toString())
  }
}
