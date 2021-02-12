package com.huangning.scala.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: 了解一些集合操作
 * Note:
 * 1. union取两个RDD的并集，两个RDD的类型要一致，不必是(K,V)格式的RDD, 分区数为二者之和
 * 2. subtract取两个RDD的差集， 两个RDD的类型要一致，不必是(K,V)格式的RDD，分区数为前面RDD的分区数
 * 3. intersection取两个RDD的交集，两个RDD的类型要一致，不必是(K,V)格式的RDD，分区数为两个RDD中分区数量大的
 */
object Trans_setOperation {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("setOperation")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val rdd1: RDD[String] = sc.parallelize(List[String]("zhangsan","lisi","wangwu","maliu"),5)
    val rdd2: RDD[String] = sc.parallelize(List[String]("a","b","c","d"),4)
    val unionRDD: RDD[String] = rdd1.union(rdd2)
    val subtractRDD: RDD[String] = rdd1.subtract(rdd2)
    val intersectionRDD: RDD[String] = rdd1.intersection(rdd2)

    unionRDD.foreach(println)
    subtractRDD.foreach(println)
    intersectionRDD.foreach(println)

    println("unionRDD partitioin length = "+unionRDD.getNumPartitions)
    println("subtractRDD partition length = "+subtractRDD.getNumPartitions)
    println("intersectionRDD partition length = "+intersectionRDD.getNumPartitions)
    sc.stop()
  }
}
