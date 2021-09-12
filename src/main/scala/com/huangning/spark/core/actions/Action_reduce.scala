package com.huangning.spark.core.actions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/19
 * Target: 了解reduce
 * Note:
 * 1. 使用指定函数聚合数据中的元素，通过使用可交换且可结合的函数保证并行计算可以得到正确的结果
 * 2. 接收两个参数返回一个参数
 */
object Action_reduce {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("countByKey").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val rdd: RDD[Int] = sc.makeRDD(Array[Int](1,2,3,4,5))
    val result: Int = rdd.reduce((v1, v2) => {
      v1 + v2
    })
    println(result)

    sc.stop()
  }
}
