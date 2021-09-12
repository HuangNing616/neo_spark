package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: 了解reduceByKey
 * Note:
 * 1. 作用在（K,V）格式的RDD上
 * 2. 首先根据key去分组，然后将每个组内的value聚合
 */
object Trans_reduceByKey {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("reduceByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val infos: RDD[(String, Int)] = sc.parallelize(List[(String,Int)](("zhangsan",1),("zhangsan",2),("zhangsan",3),("lisi",100),("lisi",200)))
    val result: RDD[(String, Int)] = infos.reduceByKey((v1, v2)=>{v1+v2})

    result.foreach(println)
    sc.stop()
  }
}
