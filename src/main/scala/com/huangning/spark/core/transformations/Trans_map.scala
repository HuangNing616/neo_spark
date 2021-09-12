package com.huangning.spark.core.transformations


import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/05
 * Target: 了解map
 */
object Trans_map {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("map").setMaster("local")
    val sc = new SparkContext(conf)

    // map处理数据是一对一的关系, 即进入一条数据处理，出来的还是一条数据
    val infos: RDD[String] = sc.parallelize(Array[String]("hello scala","hello spark","hello java"))
    val result: RDD[Array[String]] = infos.map(_.split(" "))
    result.foreach(_.foreach(println))
    sc.stop()
  }
}
