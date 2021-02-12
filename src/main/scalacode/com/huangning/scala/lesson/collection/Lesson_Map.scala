package com.huangning.scala.lesson.collection

import scala.collection.mutable

/**
 * Author: 黄宁
 * Date: 2020/06/26
 * Target: 了解Map的基本用法
 */
object Lesson_Map {
  def main(args: Array[String]): Unit = {

    // 创建Map
    val map: Map[String, Int] = Map[String, Int]("a" -> 100, "b" -> 200, ("c", 300), ("c", 400))
    val map1: Map[String, Int] = Map[String, Int](("a", 1), ("b", 2), ("c", 3), ("d", 4))
    val map2: Map[String, Int] = Map[String, Int](("a", 100), ("b", 2), ("c", 300), ("e", 500))

    // 两种遍历方法
    map.foreach(println)
    for (elem <- map) println(elem)

    /**
     * Map的方法
     * 1. .++的合并方法是后者覆盖前者
     * 2. .++:的合并方法是前者覆盖后者
     * 3. filter: 过滤出来满足条件的(k,v)
     * 4. .values/.keys: 获取Map中的values/keys
     * 5. .get("e"): 获取键为"e"对应的Some类型的值, 如果没有键则返回None
     * 6. .getOrElse("e", 10000): 获取键为"e"对应的值, 如果没有返回10000
     * 7. count: 统计符合条件的记录数
     * 8. contains: 判断map中是否包含某个key
     * 9. exists: 判断符合条件的记录是否存在
     */
    val combMap1: Map[String, Int] = map1.++(map2)
    val combMap2: Map[String, Int] = map1.++:(map2)
    val filterMap: Map[String, Int] = map.filter(elem => {
      val key: String = elem._1
      val value: Int = elem._2
      value >= 200
    })
    val valuesMap: Iterable[Int] = map.values
    val keysMap: Iterable[String] = map.keys
    val getMap1: Option[Int] = map.get("e")
    val getMap2: Int = map.getOrElse("e", 10000)
    val count: Int = map.count(elem => {
      elem._2 >= 200
    })
    val containsKey: Boolean = map.contains("b")
    val exists: Boolean = map.exists(elem => {
      elem._2 >= 200
    })

    //创建可变长的map
    val muMap: mutable.Map[String, Int] = mutable.Map[String, Int]()
    muMap.put("a", 100)
    muMap.put("b", 200)
    muMap.put("c", 300)
  }
}
