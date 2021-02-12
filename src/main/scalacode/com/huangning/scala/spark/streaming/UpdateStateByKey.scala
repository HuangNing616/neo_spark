package com.huangning.scala.spark.streaming

import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Durations, StreamingContext}
import org.apache.spark.SparkConf

/**
 * Author: 黄宁
 * Date: 2020/07/11
 * Target: 根据key进行汇总计算
 * Note:
 * 1. 根据key更新状态，所以需要设置checkpoint来保存状态
 * 2. 默认key的状态在内存中有一份，在checkpoint目录中有一份
 * 3. 多久会将内存中的数据（每个key所对应的状态）写入到磁盘上一份呢？
 *    1)如果batchinterval小于10s，那么10s会将内存上的数据写入到磁盘上一份
 *    2)如果大于10s，那么就一batchinterval为准
 *    这样做是为了防止频繁的写hdfs
 */
object UpdateStateByKey {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("UpdateStateByKey").setMaster("local[2]")

    val ssc = new StreamingContext(conf, Durations.seconds(5))
    ssc.sparkContext.setLogLevel("Error")

    val pariwords: DStream[(String, Int)] = ssc.textFileStream("file:///Users/huangning/IdeaProjects/Spark/data")
      .flatMap(_.split(" "))
      .map((_, 1))

    ssc.checkpoint("file:///Users/huangning/IdeaProjects/Spark/data/checkpoint")
    // currentValues: 当前批次某个key对应的所有value组成的集合, preValues：以往批次当前key对应的总状态值
    val result: DStream[(String, Int)] = pariwords.updateStateByKey((currentValues: Seq[Int], preValue: Option[Int]) => {
      var totalValue = 0
      if (preValue.isDefined) {
        totalValue += preValue.get
      }
      for (value <- currentValues) {
        totalValue += value
      }
      Option(totalValue)
    })
    result.print()

    ssc.start()
    ssc.awaitTermination()
    ssc.stop(false)
  }
}
