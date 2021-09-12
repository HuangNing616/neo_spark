package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/11
 * Target: 了解flatmap（一对多）
 */
object Trans_flatMap {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("flatmap").setMaster("local")
    val sc = new SparkContext(conf)
    val infos: RDD[String] = sc.parallelize(Array[String]("hello spark","hello hdfs","hello bjsxt"))
    val result: RDD[String] = infos.flatMap(_.split(" "))
    result.foreach(println)
    sc.stop()

  }
}
