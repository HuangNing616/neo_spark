package com.huangning.spark.streaming

import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Durations, StreamingContext}

/**
 * Author: 黄宁
 * Date: 2020/07/07
 * Target: 根据socket端口实时接收数据然后实现wordcount
 * Note:
 * 1. 启动socket server 服务器：nc –lk 9999
 * 2. receiver模式下接受数据，local的模拟线程必须大于等于2，一个线程用来receiver用来接受数据，另一个线程用来执行job。
 * 3. Durations时间设置就是我们能接收的延迟度。这个需要根据集群的资源情况以及任务的执行情况来调节。
 * 4. 创建JavaStreamingContext有两种方式（SparkConf,SparkContext）
 * 5. 所有的代码逻辑完成后要有一个output operation类算子。
 * 6. JavaStreamingContext.start() Streaming框架启动后不能再次添加业务逻辑。
 * 7. JavaStreamingContext.stop() 无参的stop方法将SparkContext一同关闭，stop(false)，不会关闭SparkContext。
 */
object StreamingApp {
  def main(args: Array[String]): Unit = {
    // 设置为本地运行模式，2个线程，一个监听，另一个处理数据
    val conf: SparkConf = new SparkConf().setAppName("StreamingTest").setMaster("local[2]")

    // 在streamingContext里面会根据conf创建一个sparkContext对象
    // val sc = new SparkContext(conf)
    // Durations.seconds表示batchinterval，也就是微批接收数据的时间间隔
    val ssc = new StreamingContext(conf, Durations.seconds(5))
    ssc.sparkContext.setLogLevel("Error")

    val lines: DStream[String] = ssc.textFileStream("./data")
    val words: DStream[String] = lines.flatMap(_.split(" "))
    val pairwords: DStream[(String, Int)] = words.map((_, 1))
    val result: DStream[(String, Int)] = pairwords.reduceByKey(_+_)

    // outputOperator算子print表示打印行数
    result.print(100)

    // foreachRDD存在的问题就是内部的rdd需要额外的action算子触发
    result.foreachRDD(pairRDD =>{
      println("======Driver端 动态广播黑名单============")

      val newRDD: RDD[(String, Int)] = pairRDD.filter(one => {
        println("executor in filter ====" + one)
        true
      })
      val result: RDD[Unit] = newRDD.map(one => {
        println("executor in map *****" + one)
      })
      result.count()
    })

    //一旦输入ssc.start()以后，程序就开始自动进入循环监听状态，屏幕上会显示一堆的信息
    ssc.start()
    ssc.awaitTermination()
    ssc.stop(false)
  }
}
