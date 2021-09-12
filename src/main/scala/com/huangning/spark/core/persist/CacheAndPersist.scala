package com.huangning.spark.core.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Author: 黄宁
 * Date: 2020/08/01
 * Target: 了解cachepersist
 * Note:
 * 1. cache算子默认将数据存在内存中
 * 2. persist可以手动指定持久化级别, 常用的持久化级别(存储类型)
 *    1) MEMORY_ONLY
 *    2) MEMORY_ONLY_SER
 *    3) MEMORY_AND_DISK(先存到内存，内存满了在溢写磁盘)
 *    4) MEMORY_AND_DISK_SER
 * 3. cache和persist的联系
 *    1) 持久化单位都是partition
 *    2) 都是懒执行算子, 需要action算子触发执行
 *    3) 对RDD进行cache或者persist之后可以赋值给一个变量, 下次使用这个变量就是使用持久化的数据，如果采用赋给变量之的方法，持久化算子
 *       后面不能紧跟action算子
 *    4) 可以直接对RDD进行cache或者persist而不赋值给一个变量
 *    5) 当前application执行完成之后会自动清除
 *    6) cache() =  persist(StorageLevel.MEMORY_ONLY)
 * 4. 存储级别的后缀"_2" 表示有副本
 * 5. 尽量少使用DISK_ONLY的持久化级别
 */
object CacheAndPersist {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("CacheAndPrsist").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")

    val infos: RDD[Int] = sc.makeRDD(Array[Int](1,2,3,4,5))

    infos.cache()
    val infos1: infos.type = infos.persist(StorageLevel.MEMORY_ONLY)

    // action触发了cache或者persist算子
    val startTime1: Long = System.currentTimeMillis()
    val count1: Long = infos.count()
    val endTime1: Long = System.currentTimeMillis()
    println("count1 = " + count1 + ",time = " + (endTime1-startTime1) + "ms")

    // cache持久化后操作速度加快
    val startTime2: Long = System.currentTimeMillis()
    val count2: Long = infos.count()
    val endTime2: Long = System.currentTimeMillis()
    println("count2 = " + count2 + ",time = " + (endTime2-startTime2)+"ms")

    // persist持久化后操作速度加快
    val startTime3: Long = System.currentTimeMillis()
    val count3: Long = infos1.count()
    val endTime3: Long = System.currentTimeMillis()
    println("count3 = " + count3 + ",time = " + (endTime3-startTime3)+"ms")

    sc.stop()
  }
}
