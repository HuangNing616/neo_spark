package com.huangning.scala.spark.core.transformations

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * Author: 黄宁
 * Date: 2020/07/05
 * Target: 了解cogroup
 * Note:
 * 1. cogroup将将两个RDD中相同的key结合起来，对应的value是两个sequnce构成的tuple
 * 2. interator对象可以通过toList转成List
 * 3. cogroup的结果分区个数为两个RDD中的分区个数的最大值
 * 4. rdd.getNumPartitions可以获取当前rdd的分区个数
 */
object Trans_cogroup {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("cogroup")
    val sc = new SparkContext(conf)

    val rdd1: RDD[(String, String)] = sc.parallelize(List[(String,String)](("zhangsan","female"),("zhangsan","female1"),("lisi","male"),("wangwu","female"),("maliu","male")),5)
    val rdd2: RDD[(String, Int)] = sc.parallelize(List[(String,Int)](("zhangsan",28),("lisi",39),("lisi",190),("wangwu",10),("tianqi",51)),4)

    val resultRDD: RDD[(String, (Iterable[String], Iterable[Int]))] = rdd1.cogroup(rdd2)
    resultRDD.foreach(info=>{
      val key: String = info._1
      val value1: List[String] = info._2._1.toList
      val value2: List[Int] = info._2._2.toList
      println("key = " + key + ",value1 = " + value1 + ",value2 = " + value2)
    })

    println("resultRDD partitioin length = " + resultRDD.getNumPartitions)
    sc.stop()
  }
}
