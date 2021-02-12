package com.huangning.scala.spark.core.actions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/19
 * Target: 了解take
 * Note:
 * 1. take获取正数指定个元素
 * 2. 输入指定个数，返回数组
 */
object Action_take {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("take").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val infos: RDD[Int] = sc.makeRDD(Array[Int](1,2,3,4,5))
    val result: Array[Int] = infos.take(3)
    result.foreach(println)

    sc.stop()
}
}
