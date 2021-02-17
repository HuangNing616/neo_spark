package com.huangning.scala.lesson.base

/**
 * Author: 黄宁
 * Date: 2020/06/27
 * Func: 样例类基本使用方法
 * Note:
 * 1. 样例类中的构造参数默认是val，可以直接被调用
 * 2. 普通类中的构造参数只有显式地写成val/var才能被调用
 * 3. 样例类重写了equals方法，而普通类没有
 * 4. 样例类重写了toString方法，而普通类没有
 */
object CaseClass {
  def main(args: Array[String]): Unit = {

    val s1: Student = Student("Tony", 18)
    val s2: Student = Student("John", 23)
    val s3: Student = Student("Bob", 17)

    val list: List[Student] = List[Student](s1, s2, s3)
    println("s1的年纪:", s1.age)

    // match用到了样例类重写的equals
    list.foreach { elem: Student => {
      elem match {
        case Student("Tony", 18) => println("获取到Tony的信息")
        case Student("John", 23) => println("获取到John的信息")
        case _ => println("为匹配到相关成员信息...")
      }
    }
    }

    // 上述match的简化版
    list.foreach {
      case Student("Tony", 18) => println("获取到Tony的信息")
      case Student("John", 23) => println("获取到John的信息")
      case _ => println("为匹配到相关成员信息...")
    }

    val t1: Teacher = new Teacher("Tony", 18)
    val t2: Teacher = new Teacher("John", 18)
    println("姓名修改前: " +  t2.name)
    t2.name = "Jerry"
    println("姓名修改后: " + t2.name)

    println("重写了equals方法的样例类：" + s1.equals(Student("Tony", 18)))
    println("未重写equals方法的普通类：" + t1.equals(new Teacher("Tony", 18)))

    println("重写了toString方法的样例类：" + s1.toString)
    println("未重写toString方法的普通类：" + t1.toString)

  }
}
