package com.huangning.scala.spark.core.actions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/19
 * Target: 了解first
 * Note:
 * 取出第一个元素
 */
object Action_first {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("first")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val infos: RDD[(String, Double)] = sc.parallelize(List[(String,Double)](("zhangsan",32),("lisi",24),("wangwu",51)))
    val str: (String, Double) = infos.first
    println(str)
  }
}
