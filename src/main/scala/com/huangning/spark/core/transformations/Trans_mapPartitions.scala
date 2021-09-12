package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
 * Author: 黄宁
 * Date: 2020/07/18
 * Target: 了解mapPartitions
 */

object Trans_mapPartitions {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local")setAppName("mapPartitions")
    val sc = new SparkContext(conf)

    val infos: RDD[String] = sc.parallelize(List[String]("a","b","c","d","e","f","g"),4)

    // mapPartitions遍历的是每个分区中的数据，一个个分区的遍历, 相对于map中一条条处理数据，性能比较高
    val result: RDD[String] = infos.mapPartitions(iter=>{
      println("创建数据库连接... ... ")
      val array: ArrayBuffer[String] = ArrayBuffer[String]()

      while(iter.hasNext){
        val s: String = iter.next()
        println("拼接sql... ... "+s)
        array.append(s)
      }
      println("关闭数据库连接... ... ")
      array.iterator
    })

    val count: Long = result.count()
    print(s"mapPartitions中元素的个数为$count")
    sc.stop()
  }
}
