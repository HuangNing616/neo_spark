package com.huangning.scala.spark.core.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/08/01
 * Target: 了解checkpoint
 * Note:
 * 1. 使用场景
 *    当Lineage(RDD之间的依赖关系构成的链) 中的计算逻辑非常复杂，可以尝试使用**checkpoint**将数据直接持久化到指定目录
 * 2. 执行流程
 *    1) 当Spark job完成之后，Spark会从后向前回溯，找到带有checkpoint的RDD做标记
 *    2) 回溯完成后，Spark重新启动一个job，计算带有标记的RDD数据并放入指定的checkpoint目录中
 *    3) 计算完成并放入目录后，会切断RDD的依赖关系，当Spark Application执行完成之后，指定目录中的**数据不会被清除
 * 3. 执行流程优化
 *    对RDD进行checkpoint之前先对RDD进行cache，这样执行流程的第3步就不用重新从头计算当前带有标记的RDD数据
 * 4. checkpoint一定要指定存储目录
 * 5. 与Cache，Persist的联系
 *    Cache，Persist以及Checkpoint都是懒执行，最小持久化单位是partition
 * 6. 与Cache，Persist的区别
 *    当Spark App执行完成之后会自动清除Cache，Persist的数据，但是checkpoint持久化到目录中的数据不会被清除
 */
object Checkpoint {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf =new SparkConf().setAppName("Checkpoint").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    sc.setCheckpointDir("./data/checkpoint")
    val infos: RDD[Int] = sc.makeRDD(Array[Int](1, 2, 3, 4, 5)).cache()
    infos.checkpoint()
    infos.count()
    println(s"checkpoint的路径为${infos.getCheckpointFile}")

    sc.stop()
  }
}



