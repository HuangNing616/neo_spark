package com.huangning.scala.spark.core.examples

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.util.LongAccumulator

/**
 * Author: 黄宁
 * Date: 2020/07/11
 * Target: 自定义累加器
 * Note:
 * sc.longAccumulator创建累加器对象之后常用方法.add()和.value
 */
object AccumulatorTest {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().appName("test").master("local").getOrCreate()
    val sc: SparkContext = spark.sparkContext
    sc.setLogLevel("Error")

    // 初始值默认为0
    val accum: LongAccumulator = sc.longAccumulator
    val words: RDD[String] = sc.textFile("./data/words", 5)
    var i = 0
    val rdd2: RDD[String] = words.map(one => {
      i += 1
      println(s"current i value = $i")
      accum.add(1)
      println(s"Executor accumulator = $accum")
      one
    })

    // 变量i是在executor中累加，但是打印的i是仍然是在driver端中的i
    rdd2.foreach(println)
    println(s"driver端的i值为$i")
    println(s"accumulator = ${accum.value}")
  }
}
