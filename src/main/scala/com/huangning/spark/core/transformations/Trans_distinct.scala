package com.huangning.spark.core.transformations


import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/19
 * Target: 了解distinct 去重，
 * Note:
 * 有shuffle产生，内部是根据map+reduceByKey+map实现
 */
object Trans_distinct {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("distinct")
    val sc = new SparkContext(conf)

    val infos: RDD[String] = sc.parallelize(List[String]("a","a","b","b","c","c","d"),4)
    val result: RDD[String] = infos.distinct()
    result.foreach(println)
    sc.stop()
  }
}
