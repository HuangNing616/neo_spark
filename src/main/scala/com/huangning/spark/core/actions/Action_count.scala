package com.huangning.spark.core.actions

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/08/01
 * Target: 了解count&countByKey&countByValue
 * Note:
 * 1. count统计RDD共有多少行数据
 * 2. countByKey统计相同的key出现的个数
 * 3. countByValue统计RDD中相同key下相同的value出现的次数,不必须是(K,V)格式的RDD
 */
object Action_count {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("count").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val infos: RDD[(String, Integer)] = sc.makeRDD(List[(String,Integer)](("a",1),("a",11),("a",111),("b",6),("b",66),("c",3),("c",3)))
    val result1: Long = infos.count()
    val result2: collection.Map[String, Long] = infos.countByKey()
    val result3: collection.Map[(String, Integer), Long] = infos.countByValue()

    println(s"count结果为$result1")
    println(s"countByKey结果为$result2")
    println(s"countByValue结果为$result3")

    sc.stop()
  }
}
