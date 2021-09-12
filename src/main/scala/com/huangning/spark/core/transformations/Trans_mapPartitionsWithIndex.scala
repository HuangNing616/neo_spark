package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
 * Author: 黄宁
 * Date: 2020/07/18
 * Target: 了解mapPartitionsWithIndex
 */
object Trans_mapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("mapPartitionWithIndex")
    val sc = new SparkContext(conf)

    val infos: RDD[String] = sc.makeRDD(List[String]("a", "b", "c", "d", "e", "f", "g"), 3)
    // 遍历的是每个分区中的数据, 并且给每个分区一个index, 其中preservesPartitioning表示是否保留父RDD的partitioner分区信息
    val result: RDD[String] = infos.mapPartitionsWithIndex((index, iter)=>{
      val arr: ArrayBuffer[String] = ArrayBuffer[String]()
      iter.foreach(one=>{
        arr.append(s"current partition number = 【$index】, value = $one")
      })
      arr.iterator
    }, preservesPartitioning = true)
    result.foreach(println)
    sc.stop()
  }
}
