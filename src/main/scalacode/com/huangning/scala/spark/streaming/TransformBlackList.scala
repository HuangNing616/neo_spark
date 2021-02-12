package com.huangning.scala.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Duration, Durations, StreamingContext}

object TransformBlackList {
  def main(args: Array[String]): Unit = {
    val conf:SparkConf = new SparkConf().setAppName("transform").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Durations.seconds(5))
    ssc.sparkContext.setLogLevel("Error")

    // 广播黑名单
    val blackList: Broadcast[List[String]] = ssc.sparkContext.broadcast(List[String]("zhangsan", "lisi"))

    // 从实时数据中发现第二位是黑名单，直接过滤掉
    val lines: DStream[String] = ssc.textFileStream("file:///Users/huangning/IdeaProjects/Spark/data")
    val pairLines: DStream[(String, String)] = lines.map(line => {
      (line.split(" ")(1), line)
    })

    /**
     * 1. transform中拿到的RDD的算子之外，代码是在driver端执行的，可以做到动态的改变广播变量
     * 2. transform可以拿到DStream中的RDD，对RDD使用RDD的算子操作, 但是要返回RDD，并且最后返回的RDD又会自动被封装到DStream
     */
    val resultDStream: DStream[String] = pairLines.transform((pairRDD: RDD[(String, String)]) => {
      println("++++++++++ Driver code +++++++++++++")
      val filterRDD: RDD[(String, String)] = pairRDD.filter(tuple => {
        val nameList: List[String] = blackList.value
        !nameList.contains(tuple._1)
      })

      val returnRDD: RDD[String] = filterRDD.map(tp => tp._2)
      returnRDD
    })

    resultDStream.print()

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }
}
