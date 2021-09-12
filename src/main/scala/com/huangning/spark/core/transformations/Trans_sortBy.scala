package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: 了解sortBy & sortByKey
 * Note:
 * 1. sortBy的第一个参数指定按照什么规则去排序，第二个参数true/false指定升序或者降序
 * 2. sortBy不用非要作用在(K,V)格式的RDD上, sortByKey一定作用在(K,V)格式的RDD上
 * 3. sortByKey按照key排序
 */
object Trans_sortBy {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("sortBy&sortByKey")
    val sc = new SparkContext(conf)

    val rdd: RDD[Int] = sc.parallelize(Array[Int](400,200,500,100,300))
    val infos: RDD[(String, Int)] = sc.parallelize(Array[(String,Int)](("f",1),("a",3),("c",2),("b",1)))

    // sortBy针对RDD中的元素取反排序
    val result: RDD[Int] = rdd.sortBy(one=>{-one},ascending = true)
    result.foreach(println)

    // sortBy针对(K,V)格式RDD中的V去排序
    val result1: RDD[(String, Int)] = infos.sortBy(tp=>{
      tp._2
    },ascending = false)
    result1.foreach(println)

    // sortByKey针对(K,V)格式RDD中的K去排序
    val result2: RDD[(String, Int)] = infos.sortByKey(ascending = false)
    result2.foreach(println)

    sc.stop()
  }
}
