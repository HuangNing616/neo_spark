package com.huangning.scala.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Durations, StreamingContext}

/**
 * Author: 黄宁
 * Date: 2020/07/11
 * Target: 了解window窗口函数，实现每隔一分钟看过去一个小时统计结果的功能
 */
object WindowOperator {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("WindowOperator")
    conf.setMaster("local[2]")

    val ssc = new StreamingContext(conf, Durations.seconds(5))
    ssc.sparkContext.setLogLevel("Error")
    val lines: DStream[String] = ssc.textFileStream("file:///Users/huangning/IdeaProjects/Spark/data")
    val pairwords: DStream[(String, Int)] = lines.flatMap(_.split(" "))
      .map((_, 1))

    /**
     * 窗口操作的普通机制:
     * 参数：计算逻辑 + 窗口长度（window length） + 窗口滑动间隔(window sliding interval)
     */
    val result: DStream[(String, Int)] = pairwords.reduceByKeyAndWindow((v1: Int, v2: Int)=>{v1+v2}, Durations.seconds(15), Durations.seconds(10))
    result.print()

    /**
     * 窗口的优化机制: 状态保留，即不需要每次都计算窗口长度内的结果，只需要计算滑动窗口的时候值减少了多少和增加了多少
     */
    ssc.checkpoint("file:///Users/huangning/IdeaProjects/Spark/data/checkpoint")
    val result2: DStream[(String, Int)] = pairwords.reduceByKeyAndWindow((v1: Int, v2: Int) => {
      v1 + v2
    },
      (v1: Int, v2: Int) => {
        v1 - v2
      },
      Durations.seconds(15),
      Durations.seconds(5))
    result2.print()

    /**
     * 通过自己的逻辑去使用窗口函数
     */
    val result3: DStream[(String, Int)] = pairwords.window(Durations.seconds(15), Durations.seconds(5))
    result3.print()

    ssc.start()
    ssc.awaitTermination()
    ssc.stop(false)

  }
}
