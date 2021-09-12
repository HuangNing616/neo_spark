package com.huangning.spark.core.actions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/19
 * Target: 了解collect&collectAsMap
 * Note:
 * 1. collect会将结果回收到Driver端，如果结果比较大，就不要回收，这样的话会造成Driver端的OOM
 * 2. collectAsMap会将(K,V)格式的RDD回收到Driver端作为Map使用
 */
object Action_collect {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("collect").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val infos: RDD[(String, Double)] = sc.parallelize(List[(String,Double)](("zhangsan",32),("lisi",24),("wangwu",51)))
    val resultArr: Array[(String, Double)] = infos.collect()
    val resultMap: collection.Map[String, Double] = infos.collectAsMap()

    resultArr.foreach(println)
    resultMap.foreach(println)

    sc.stop()
  }
}

