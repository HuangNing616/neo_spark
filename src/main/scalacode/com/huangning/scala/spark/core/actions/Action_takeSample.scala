package com.huangning.scala.spark.core.actions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/19
 * Target: 了解takeSample(withReplacement,num,seed)
 * Note:
 * 1. 随机抽样将数据结果拿回Driver端使用，返回Array。
 * 2. 参数说明
 *    1) withReplacement:有无放回抽样
 *    2) num:抽样的条数
 *    3) seed:种子
 */
object Action_takeSample {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("takeSample")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val infos: RDD[Int] = sc.makeRDD(Array[Int](1,2,3,4,5))
    val result: Array[Int] = infos.takeSample(withReplacement = false, 300, 100)
    result.foreach(println)

    sc.stop()
  }
}
