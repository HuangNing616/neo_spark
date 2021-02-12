package com.huangning.scala.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: sample随机抽样
 * Note:
 * 1. 参数(有无放回抽样，抽样的比例，种子)
 * 2. 有种子和无种子的区别
 *    1) 如果有种子，那么针对同一数据源并且指定相同的参数，那么每次抽样到的数据都是一样的
 *    2) 如果没有种子, 那么针对同一数据源，每次抽样都是随机抽样
 */
object Trans_sample {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("sample").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val lines: RDD[Int] = sc.makeRDD(List[Int](1,2,4,12,3,1))
    val result: RDD[Int] = lines.sample(withReplacement = true,1,100)

    result.foreach(println)
    sc.stop()
  }
}
