package com.huangning.scala.spark.core.examples

import java.lang.Math.random

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 * Author: huangning
 * Date: 2020/10/20
 * Target: 提交Spark
 * Note:
 * 1. 本地RDD一个分区
 */
object SparkPi {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder
      .appName("Spark Pi")
      .getOrCreate()

    val slices: Int = if (args.length > 0) args(0).toInt else 2
    val n: Int = math.min(100000L * slices, Int.MaxValue).toInt
    val count: Int = spark.sparkContext.parallelize(1 until n, slices).map { i =>
      val x: Double = random * 2 - 1
      val y: Double = random * 2 - 1
      if (x * x + y * y <= 1) 1 else 0
    }.reduce(_ + _)

    println(s"Pi is roughly ${4.0 * count / (n - 1)}")

//    val sc: SparkContext = spark.sparkContext
//    val rdd: RDD[String] = sc.parallelize(List[String]("aaa", "bbb", "ccc", "ddd"), 2)
//    println(rdd.getNumPartitions)


    spark.stop()
  }
}
