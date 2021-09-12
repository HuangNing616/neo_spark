package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: 了解zip & zipWithIndex
 * Note:
 * 1. zip将两个RDD合成一个（K,V）格式的RDD
 * 2. zip要求分区数相同，每个分区中的元素必须相同
 * 3. zipWithIndex将RDD和数据下标压缩成一个K,V格式的RDD
 */
object Trans_zip {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("zip")
    val sc = new SparkContext(conf)

    val rdd1: RDD[String] = sc.parallelize(List[String]("a","b","c"),2)
    val rdd2: RDD[Int] = sc.parallelize(List[Int](1,2,3),numSlices = 2)
    val result1: RDD[(String, Int)] = rdd1.zip(rdd2)
    val result2: RDD[(String, Long)] = rdd1.zipWithIndex()

    result1.foreach(println)
    result2.foreach(println)
    sc.stop()
  }
}
