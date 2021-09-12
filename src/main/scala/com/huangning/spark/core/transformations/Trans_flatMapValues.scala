package com.huangning.spark.core.transformations

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: 了解flatMapValues
 * Note:
 * 1. 作用在在（K,V）格式的RDD上
 * 2. 对一个Key的一个Value返回多个Value
 */
object Trans_flatMapValues {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("flatMapValues").setMaster("local")
    val sc = new SparkContext(conf)

    val infos: RDD[(String, String)] = sc.makeRDD(List[(String, String)](("zhangsan", "32"), ("lisi", "24"), ("wangwu", "31")))
    val transInfo: RDD[(String, String)] = infos.mapValues(s => {
      s + " " + "loveme"
    })
    val result: RDD[(String, String)] = transInfo.flatMapValues(s=>{
      s.split(" ")
    })

    result.foreach(println)
    sc.stop()
  }
}
