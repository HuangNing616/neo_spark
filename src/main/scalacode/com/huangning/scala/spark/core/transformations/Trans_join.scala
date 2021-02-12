package com.huangning.scala.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/25
 * Target: 了解join&leftOuterJoin&rightOuterJoin&fullOuterJoin
 * Note:
 * 1. 产生shuffle
 * 2. 针对（K,V）格式的RDD和（K,W）格式的RDD
 *    1) join按照相同的key进行join得到（K,(V,W)）格式的数据,
 *    2) leftOuterJoin是以左边的RDD出现的key为主，得到（K,(V,Option(W))）
 *    3) rightOuterJoin是以右边的RDD出现的key为主，得到（K,(V,Option(W))）
 *    4) fullOuterJoin是以两边的RDD出现的key为主，得到（K,(Option(V)，Option(W))）
 * 3. 无论何种方式join，结果分区的数量都是二者最大的那个
 */
object Trans_join {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("join").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val nameRDD: RDD[(String, String)] = sc.parallelize(List[(String,String)](("zhangsan","female"),("lisi","male"),("wangwu","female"),("maliu","male")), numSlices = 3)
    val scoreRDD: RDD[(String, Int)] = sc.parallelize(List[(String,Int)](("zhangsan",18),("lisi",19),("wangwu",20), ("tianqi",21)), numSlices = 4)
    val joinRDD: RDD[(String, (String, Int))] = nameRDD.join(scoreRDD)
    val leftJoinRDD: RDD[(String, (String, Option[Int]))] = nameRDD.leftOuterJoin(scoreRDD)
    val rightJoinRDD: RDD[(String, (Option[String], Int))] = nameRDD.rightOuterJoin(scoreRDD)
    val fullJoinRDD: RDD[(String, (Option[String], Option[Int]))] = nameRDD.fullOuterJoin(scoreRDD)

    joinRDD.foreach(println)
    leftJoinRDD.foreach(println)
    rightJoinRDD.foreach(println)
    fullJoinRDD.foreach(println)
    println(s"join的分区个数是${joinRDD.getNumPartitions}")
    println(s"leftJoin的分区个数是${leftJoinRDD.getNumPartitions}")
    println(s"rightJoin的分区个数是${rightJoinRDD.getNumPartitions}")
    println(s"fullJoin的分区个数是${fullJoinRDD.getNumPartitions}")
    sc.stop()
  }
}
