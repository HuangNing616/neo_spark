package com.huangning.scala.lesson.base


/**
 * Author: 黄宁
 * Date: 2021/02/17
 * Func: 字符串
 */
object String {
  def main(args: Array[String]): Unit = {

    // 创建字符串
    val str1: String = "John"
    val str2: String = "john"
    val str3: String = "Tony"

    // 获取字符串中某个元素的索引
    println(s"字符串中首个表示n的ascii码的元素下标:${str1.indexOf(110)}")
    println(s"字符串中首个表示'n'的元素下标:" + str1.indexOf("n"))
    println(s"字符串中如果找不到指定元素返回:" + str1.indexOf("z"))

    // 比较两个字符串是否相等
    println(s"不忽略大小写比较：${str1 == str2}")
    println(s"不忽略大小写比较：${str1.equals(str2)}")
    println(s"忽略大小写比较：${str1.equalsIgnoreCase(str2)}")

    // 忽略大小写的字符串大小比较
    println(s"前者等于后者的结果：${str1.compareToIgnoreCase(str2)}")
    println(s"前者大于后者的结果：${str3.compareToIgnoreCase(str1)}")
    println(s"前者小于后者的结果：${str1.compareToIgnoreCase(str3)}")

    // 不忽略大小写的字符串大小
    println(s"前者等于后者的结果：${str1.compareTo(str1)}")
    println(s"前者大于后者的结果：${str2.compareTo(str1)}")
    println(s"前者小于后者的结果：${str1.compareTo(str2)}")

    // 字符串遍历
    println("第一种字符串遍历")
    for (i <- str1) print(i + "-")
    println()
    println("第二种字符串遍历")
    str1.foreach((elem: Char) => print(elem + "-"))
    println()
    str1.foreach(print)
    println()

    // 可变字符串
    val st: StringBuilder = new StringBuilder

    st.++=("abc")
    st ++= "efg"

    st.+=('h')
    st += 'i'

    st.append(1.0)
    st.append("==>")
    st.append(18f)

    println(s"添加元素后的字符串: $st")
  }
}
