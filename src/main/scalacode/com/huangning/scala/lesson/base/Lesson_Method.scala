package com.huangning.scala.lesson.base

import java.util.Date

/**
 * Author: 黄宁
 * Date: 2020/06/20
 * Target: 了解函数与方法
 */
object Lesson_Method {

  /**
   *  1. 方法定义
   *     (1) def 来定义方法
   *     (2) 定义方法传入的参数一定要指定类型
   *     (3) 如果定义方法时，省略了方法名称和方法体之间的"="，那么无论方法体最后一行计算的结果是什么，都会被丢弃，返回Unit
   *     (4) 方法的方法体可以如果可以一行搞定，那么方法体的花括号可以省略
   *     (5) 方法体中最后返回值可以使用return，那么方法体的返回值类型一定要指定
   *     (6) 方法体中没有return，默认将方法体中最后一行的计算结果当作返回值，那么方法体的返回值类型可以省略，会自动推断返回值的类型
   */
  def max(a: Int, b: Int): Int = {
    if (a > b) {
      a
    } else {
      b
    }
  }

  def max2(a: Int, b: Int): Int = if (a > b) a else b

  /**
   * 2.递归方法
   * (1) 递归方法一定要指定返回值类型
   * (2) 递归方法的前面有小圆圈
   */
  def recursionFun(num: Int): Int = {
    if (num == 1) {
      1
    } else {
      num * recursionFun(num - 1)
    }
  }

  /**
   * 3.参数有默认值的方法
   * (1) 当传递参数个数小于指定参数列表个数，那么一定要指定传递的是哪个参数，不然实参就会一个一个传递给形参
   */
  def defaultValue(a: Int = 10, b: Int): Int = {
    a + b
  }

  /**
   * 4.可变长参数的方法
   */
  def variableLength(s: String*): Unit = {
    s.foreach((elem: String) => {
      print(" => " + elem)
    })
    println()
  }

  /**
   * 5. 匿名函数
   * (1) "=>"就是匿名函数，
   * (2) 当方法的参数是函数时，经常用匿名函数
   * (3) 可以将匿名函数赋值给变量，方便在其他地方调用, 语法: def/val/var 变量名：（函数的输入类型） => 函数的输出类型
   * (4) 可以将方法赋值给变量，变量类型是函数, 但是一定要手动指定函数类型
   */
  def lambadFun: (Int, Int) => Int = (a: Int, b: Int) => {
    a + b
  }

  def lambadMethod(a: Int): Unit = {
    println(a)
  }

  val lambdaTest: Int => Unit = lambadMethod

  /**
   * 6. 嵌套方法
   */
  def nestingFun(num: Int): Int = {
    def fun(a: Int): Int = if (a == 1) 1 else a * fun(a - 1)

    fun(num)
  }

  /**
   * 7.偏应用函数
   * (1) 当方法中参数非常多，调用这个方法非常频繁并且每次调用只有固定的某个参数变化，其他都不变，可以使用偏应用来实现
   */
  def showLog(date: Date, log: String): Unit = {
    println(s"时间是 $date , 日志是 $log ")
  }

  val date = new Date()

  def partialAppFun: String => Unit = showLog(date, _: String)

  /**
   * 8. 高阶函数
   * (1) 方法的参数是函数
   * (2）方法的返回是函数, <要显式地将返回值写成相应函数类型, 才能将相应的方法转成函数并作为返回值, 不然就会报错
   * 因为编译器看到方法名不知道接下是返回相应的函数，还是调用该方法。如果加 _ 强制将方法转成函数并作为返回值， 也就是可以不显示的声明方法的返回值类型>
   * (3）方法的参数和返回都是函数
   */
  def paramFun(f: (Int, Int) => Int, s: String): String = {
    val i: Int = f(100, 200)
    i + " # " + s
  }

  def returnFun1(s: String): (String, String) => String = {

    def fun(s1: String, s2: String): String = {
      s1 + "~" + s2 + "#" + s
    }

    fun
  }

  def returnFun2(s: String) = {

    def fun(s1: String, s2: String): String = {
      s1 + "~" + s2 + "#" + s
    }

    fun _
  }

  def paramAndReturnFun(f: (Int, Int) => Int): (String, String) => String = {
    val i: Int = f(10, 90)

    def fun(s1: String, s2: String): String = {
      s1 + "@" + s2 + "*" + i
    }

    fun
  }

  /**
   * 9. 柯里化函数:就是高阶函数第二种方式的简化写法，
   */
  def curryingFun(a: Int, b: Int)(c: Int, d: Int): Int = {
    a + b + c + d
  }

  def main(args: Array[String]): Unit = {
    val result10: Int = max(100, 20)
    val result11: Int = max2(100, 20)
    val result2: Int = recursionFun(5)
    val result3: Int = defaultValue(b = 2)
    val result5: Int = lambadFun(1, 3)
    val result6: Int = nestingFun(5)
    val result80: String = paramFun((a, b) => {
      a * b
    }, "scala")
    val result81: String = returnFun1("a")("b", "c")
    val result82: String = returnFun1("a")("b", "c")
    val result83: String = paramAndReturnFun((a, b) => {
      a + b
    })("huang", "ning")
    val result9: Int = curryingFun(1, 2)(3, 4)

    println("1. max方法的结果：" + result10)
    println("1. max方法写成1行的结果：" + result11)
    println("2. 递归方法的结果：" + result2)
    println("3. 参数有默认值的方法的结果：" + result3)
    println("4. 可变长参数方法的结果：")
    variableLength("www", "d", "dd", "a")
    println("5. 匿名函数的结果：" + result5)
    println("6. 嵌套方法的结果：" + result6)
    println("7. 偏应用函数的结果")
    partialAppFun("aaa")
    partialAppFun("bbb")
    partialAppFun("ccc")
    println("8. 高阶函数--方法的参数是函数的结果：" + result80)
    println("8. 高阶函数--方法的返回值是函数的结果：" + result81)
    println("8. 高阶函数--方法的返回值是函数的结果：" + result82)
    println("8. 高阶函数--方法和返回值都是函数的结果：" + result83)
    println("9. 柯里化函数的结果：" + result9)
  }
}
