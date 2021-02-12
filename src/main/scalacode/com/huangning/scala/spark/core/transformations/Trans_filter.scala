package com.huangning.scala.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/24
 * Target: filter 过滤算子
 * Note:
 * filter过滤数据，返回true的数据会被留下
 */
object Trans_filter {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("filter").setMaster("local")
    val sc = new SparkContext(conf)

    val infos: RDD[Int] = sc.makeRDD(List[Int](11,22,32,46,83))
    val result: RDD[Int] = infos.filter(one=>{
      one>50
    })
    result.foreach(println)
    sc.stop()
  }

}
