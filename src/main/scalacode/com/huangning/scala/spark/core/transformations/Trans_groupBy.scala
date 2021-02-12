package com.huangning.scala.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: 了解groupBy
 * Note:
 * 1. groupBy按照指定的规则，将数据分组，不必是(K,V)格式的RDD
 * 2. groupByKey将相同的key对应的value合并在一起
 */
object Trans_groupBy {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("groupBy")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val info: RDD[String] = sc.parallelize(List[String](
      "love1", "love2", "love3", "love4",
      "love5", "love6", "love7", "love8",
      "love9", "love10", "love11", "love12"),3)
    val rdd: RDD[(String, Double)] = sc.parallelize(List[(String,Double)](("zhangsan",66.5),("lisi",33.2),
      ("zhangsan",66.7),("lisi",33.4),("zhangsan",66.8),("wangwu",29.8)))

    // groupBy可以针对非(K,V)格式的RDD
    val result: RDD[(String, Iterable[String])] = info.groupBy(one=>{one.split("")(4)})
    result.foreach(println)

    // groupBy也可以针对(K,V)格式的RDD
    val resultBy: RDD[(Boolean, Iterable[(String, Double)])] = rdd.groupBy(one => {
      one._2 > 34
    })
    resultBy.foreach(println)

    // groupByKey针对的是(K,V)格式的RDD
    val resultByKey: RDD[(String, Iterable[Double])] = rdd.groupByKey()
    resultByKey.foreach(info=>{
      val name: String = info._1
      val iterable: Iterable[Double] = info._2
      val list: List[Double] = info._2.toList
      println("name = " + name + ",iterable = " + iterable + ",list = " + list)
    })

    sc.stop()
  }
}
