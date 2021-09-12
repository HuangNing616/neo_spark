package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
 * Author: 黄宁
 * Date: 2020/07/18
 * Target: 了解combineByKey
 * Note:
 * 1. 作用在（K,V）格式的RDD上
 * 2. 执行流程
 *  1）. 首先给RDD中每个分区中的每个key一个初始值
 *  2）. 其次在RDD每个分区内部 相同的key聚合一次
 *  3）. 最后在RDD不同的分区之间将相同的key结果聚合一次
 */
object Trans_combineByKey {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("combineByKey")
    val sc = new SparkContext(conf)

    val infos: RDD[(String, Int)] = sc.makeRDD(List[(String, Int)](
      ("zhangsan", 10), ("zhangsan", 20), ("wangwu", 30),
      ("lisi", 40), ("zhangsan", 50), ("lisi", 60),
      ("wangwu", 70), ("wangwu", 80), ("lisi", 90)
    ), 3)

    // 给每个分区标上索引
    infos.mapPartitionsWithIndex((index,iter)=>{
      val arr: ArrayBuffer[(String, Int)] = ArrayBuffer[(String,Int)]()

      iter.foreach(value=>{
        arr.append(value)
        println(s"rdd1 partition index = 【$index】 ,value = 【$value】")
      })

      arr.iterator
    }).count()

    /**
      * 0号分区：("zhangsan", 10), ("zhangsan", 20), ("wangwu", 30)
      * 1号分区：("lisi", 40), ("zhangsan", 50), ("lisi", 60)
      * 2号分区：("wangwu", 70), ("wangwu", 80), ("lisi", 90)
      *
      * 初始化后：
      * 0号分区：("zhangsan", 10hello),("wangwu", 30hello)
      * 1号分区：("lisi", 40hello), ("zhangsan", 50hello)
      * 2号分区：("wangwu", 70hello),("lisi", 90hello)
      *
      * 经过RDD分区内的合并后:
      * 0号分区：("zhangsan", 10hello@20)，("wangwu", 30hello)
      * 1号分区：("lisi", 40hello@60), ("zhangsan", 50hello)
      * 2号分区：("wangwu", 70hello@80),("lisi", 90hello#50hello)
      *
      * 经过RDD分区之间的合并：("zhangsan", 10hello@20#50hello),("lisi",40hello@60#90hello),("wangwu", 30hello#70hello@80)
      */
    val result: RDD[(String, String)] = infos.combineByKey(
      v=>{v + "hello"},
      (in1:String, in2)=>{in1 + "@" + in2},
      (out1:String, out2:String)=>{out1 + "#" + out2})
    result.foreach(println)
    sc.stop()
  }
}
