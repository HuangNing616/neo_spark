package com.huangning.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Durations, StreamingContext}

/**
 * Author: 黄宁
 * Date: 2020/07/19
 * Target: 了解sparkHA
 * 1. SparkStreaming Driver要一直启动，如果挂掉的话Application就挂掉
 * 2. 在提交application的时候（--submit xxxx），添加 --supervise选项，如果Driver挂掉会自动重新启动
 * 3. Driver HA 主要用在当停止SparkStreaming，再次启动的时候，SparkStreaming可以接着上次消费的数据继续消费
 */
object SparkStreamingDriverHA {
  val checkpointDir = "./data/streamingCheckpoint"
  def main(args: Array[String]): Unit = {
    /**
     * StreamingContext.getOrCreate(ckDir, CreateStreamingContext)
     * 这个方法首先会从ckDir目录中获取StreamingContext， 因为StreamingContext是序列化存储在Checkpoint目录中，恢复时尝试反序列化这些内容，若能拿回StreamingContext，就不会执行CreateStreamingContext这个方法，否则会执行这个方法
     */
    val ssc: StreamingContext = StreamingContext.getOrCreate(checkpointDir, CreateStreamingContext)
    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }

  def CreateStreamingContext(): StreamingContext = {
    println("===========Create new StreamingContext=========")
    val conf: SparkConf = new SparkConf().setAppName("DriverHA").setMaster("local")
    val ssc = new StreamingContext(conf, Durations.seconds(5))
    ssc.sparkContext.setLogLevel("Error")

    /**
     *  通过checkpoint来实现Driver的HA
     *  默认checkpoint存储的内容分成下述四个方面：
     *  1. 配置信息
     *  2. DStream操作逻辑
     *  3. job的执行速度
     *  4. offset
     */
    ssc.checkpoint(checkpointDir)
    val result: DStream[(String, Int)] = ssc.textFileStream("./data/streamingCopyFile")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
    result.print(2)

    // 如果通过checkpoint路径来恢复streamingConctext，那么下述更改的逻辑并不能被执行，除非换checkpoint路径或者删除当前checkpoint路径
    result.foreachRDD(pairRDD => {
      val value: RDD[(String, Int)] = pairRDD.filter(one => {
        println(" ************* filter ***********")
        true
      })
      value.foreach(println)
    })
    ssc
  }
}
