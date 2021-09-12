package com.huangning.spark.core.examples

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/18
 * Target: 了解broadcast
 * Note:
 * sc.broadcast(xxx)创建广播变量的对象之后常用方法.value
 */
object BroadCastTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test")
      .setMaster("local")

    val sc = new SparkContext(conf)
    val list: Seq[String] = List[String]("zhangsan","lisi")
    val bcList: Broadcast[Seq[String]] = sc.broadcast(list)
    val nameRDD: RDD[String] = sc.parallelize(List[String]("zhangsan", "lisi", "wangwu", "zhaoliu"))

    val result: RDD[String] = nameRDD.filter(name => {
      val innerlist: Seq[String] = bcList.value
      !innerlist.contains(name)
    })

    result.foreach(println)
  }
}
