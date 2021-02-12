package com.huangning.scala.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * Author: 黄宁
 * Date: 2020/07/24
 * Target: 了解coalesce
 * Note:
 * 1. coalesce 增加或者减少分区，默认shuffle = false, 如果从少的分区增到多的分区，必须指定shuffle = true, 否则不起作用
 * 2. repartition可以将RDD的分区增多或者减少，会产生shuffle（宽依赖算子）
 * 3. coalesce(num,true) = repartition(num), 会产生shuffle（宽依赖算子）
 */
object Trans_coalesce {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("coalesce")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val rdd: RDD[String] = sc.parallelize(List[String](
      "love1", "love2", "love3", "love4",
      "love5", "love6", "love7", "love8",
      "love9", "love10", "love11", "love12"),3)

    val infos :RDD[String] = rdd.mapPartitionsWithIndex((index,iter)=>{
      val list: ListBuffer[String] = ListBuffer[String]()
      iter.foreach(one=>{
        list.append(s"rdd partition = 【$index】,value = 【$one】")
      })
      list.iterator
    },preservesPartitioning = true)

    // 分区个数从3增加到4
    val resultRDD: RDD[String] = infos.coalesce(4, shuffle = true)
    resultRDD.mapPartitionsWithIndex((index,iter)=>{
      val arr: ArrayBuffer[String] = ArrayBuffer[String]()
      iter.foreach(one=>{
        arr.append(s"resultRDD partition = 【$index】,value =  【$one】")
      })
      arr.iterator
    }).foreach(println)

    // 分区个数从3变成3，依然会有shuffle
    val result2RDD: RDD[String] = infos.repartition(3)
    result2RDD.mapPartitionsWithIndex((index,iter)=>{
      val arr: ArrayBuffer[String] = ArrayBuffer[String]()
      iter.foreach(one=>{
        arr.append(s"result2RDD partition = 【$index】,value =  【$one】")
      })
      arr.iterator
    }).foreach(println)

    sc.stop()
  }
}
