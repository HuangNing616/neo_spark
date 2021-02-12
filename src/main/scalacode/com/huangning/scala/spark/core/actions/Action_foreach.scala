package com.huangning.scala.spark.core.actions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/24
 * Target: 了解foreach
 * Note:
 * 1. foreach遍历RDD中的每个元素
 * 2. mapPartitions相比于foreach的好处是几个分区就创建几个对象，减少创建链接的次数，是一个高性能算子
 * 3. mapPartitions相比于mapPartitions的应用场景是，插入数据库之后不再有其他操作就用foreachPartitions, 如果还有其他操作就用mapPartitions
 */
object Action_foreach {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("foreach").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val infos: RDD[String] = sc.parallelize(List[String]("a","b","c","d","e","f","g"),4)
    infos.foreach(println)
    infos.foreachPartition(iter => {
      println("创建数据库")
      while (iter.hasNext) {
        val elem: String = iter.next()
        println("插入数据库..." + elem)
      }
      println("关闭数据库")
    })
    sc.stop()
  }
}
