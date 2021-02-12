package com.huangning.scala.spark.core.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/07/11
 * Target: 用scala实现wordcount
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    // conf 可以设置SparkApplication（就是一段spark代码）的名称，设置spark运行的模式
    val conf: SparkConf = new SparkConf()
      .setAppName("wordcount")
      .setMaster("local")
      .set("spark.driver.memory", "100")

    //SparkContext 是通往spark集群的唯一通道
    val sc = new SparkContext(conf)

    val lines: RDD[String] = sc.textFile("./data/words")
    val words: RDD[String] = lines.flatMap(line => {
      line.split(" ")
    })
    val pairWords: RDD[(String, Int)] = words.map(word => {
      (word, 1)
    })
    val result: RDD[(String, Int)] = pairWords.reduceByKey((v1, v2) => {
      v1 + v2
    })
    val sortResult: RDD[(String, Int)] = result.sortBy(tuple => {
      tuple._2
    }, ascending = false)

    sortResult.foreach(line => {
      println(line)
    })

    // wordcount简洁版
    sc.textFile("./data/words")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_+_)
      .sortBy(_._2)
      .foreach(println)

    sc.stop()
  }
}
