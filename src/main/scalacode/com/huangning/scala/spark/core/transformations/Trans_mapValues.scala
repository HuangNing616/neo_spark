package com.huangning.scala.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/24
 * Target: 了解mapValues
 * Note:
 * 针对(K,V)格式的数据，只对Value做操作，Key保持不变
 */
object Trans_mapValues {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("mapValues").setMaster("local")
    val sc = new SparkContext(conf)

    val infos: RDD[(String, String)] = sc.makeRDD(List[(String, String)](("zhangsna", "18"), ("lisi", "20"), ("wangwu", "30")))
    val result: RDD[(String, String)] = infos.mapValues(s => {
      s + "~" + "loveyou"
    })

    result.foreach(print)
    sc.stop()
  }
}
