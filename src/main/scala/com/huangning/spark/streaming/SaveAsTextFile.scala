package com.huangning.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Durations, StreamingContext}


/**
 * Author: 黄宁
 * Date: 2020/07/18
 * Target: 将SparkSteaming处理的结果保存在指定的目录中
 * Note:
 * 1. saveAsTextFiles(prefix, [suffix])：将此DStream的内容另存为文本文件, 每批次数据产生的文件名称格式基于：prefix和suffix: "prefix-xxxxxx-suffix".
 * 2. saveAsTextFile是调用saveAsHadoopFile实现的
 * 3. spark中普通rdd可以直接只用saveAsTextFile(path)的方式，保存到本地，但是此时DStream的只有saveAsTextFiles()方法，没有传入路径的方法，其参数只有prefix, suffix.
 * 4. DStream中的saveAsTextFiles方法中又调用了rdd中的saveAsTextFile方法，我们需要将path包含在prefix中
 * 5. sparkStreaming 监控本地某个目录数据时，这个目录下已经存在的文件不会被监控到，可以监控到增加的文件, 增加的文件必须是原子性产生（一次性产生），已经存在的文件后面追加信息的文件，或者被删除的文件不能被监控到
 */
object SaveAsTextFile {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("SaveAsTextFile").setMaster("local")
    val ssc = new StreamingContext(conf, Durations.seconds(5))
    ssc.sparkContext.setLogLevel("Error")

    val lines: DStream[String] = ssc.textFileStream("./data/streamingCopyFile")
    val words: DStream[String] = lines.flatMap(line => {
      line.split(" ")
    })
    val pairWords: DStream[(String, Int)] = words.map((_, 1))
    val result: DStream[(String, Int)] = pairWords.reduceByKey((v1: Int, v2: Int) => {
      v1 + v2
    })

    // 保存的多级目录写入前缀之中
    result.saveAsTextFiles("./data/streamingSavePath/prefex", "suffix")

    ssc.start()
    ssc.awaitTermination()
    ssc.start()

  }
}
