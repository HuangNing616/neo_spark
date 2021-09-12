package com.huangning.spark.core.transformations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
 * Author: 黄宁
 * Date: 2020/07/24
 * Target: 了解aggregateByKey
 * Note:
 * 1. 作用在（K,V）格式的RDD上
 * 2. 执行流程
 *  1）. 首先给RDD中每个分区中的每个key一个初始值
 *  2）. 其次在RDD每个分区内部 相同的key聚合一次
 *  3）. 最后在RDD不同的分区之间将相同的key结果聚合一次
 * 3. 和combineByKey是一样的，唯一区别在于aggregateByKey在第一个初始化的逻辑上使用了柯里化的形式
 */

object Trans_aggregateByKey {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("aggregateByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val infos: RDD[(String, Int)] = sc.makeRDD(List[(String,Int)](
      ("zhangsan",10),("zhangsan",20),("wangwu",30),
      ("lisi",40),("zhangsan",50),("lisi",60),
      ("wangwu",70),("wangwu",80),("lisi",90)
    ),3)

    /**
      *  0号分区：
      *     ("zhangsan",10)
      *     ("zhangsan",20)
      *     ("wangwu",30)
      *  1号分区：
      *     ("lisi",40)
      *     ("zhangsan",50)
      *     ("lisi",60)
      *  2号分区：
      *     ("wangwu",70)
      *     ("wangwu",80)
      *     ("lisi",90)
      *
      *  init :
      *   0:("zhangsan",hello~10~20),("wangwu",hello~30)
      *   1:("zhangsan",hello~50),("lisi"，hello~40~60)
      *   2:("lisi",hello~90)，("wangwu",hello~70~80)
      *
      *   分区合并后：("zhangsan",hello~10~20#hello~50),("lisi",hello~40~60#hello~90),("wangwu",hello~30#hello~70~80)
      *   ("zhangsan")
      */
    val result: RDD[(String, String)] = infos.aggregateByKey("hello")(
      (s, v)=>{s+"~"+v},
      (s1, s2)=>{s1+"#"+s2})

    result.foreach(print)
    sc.stop()
  }
}
