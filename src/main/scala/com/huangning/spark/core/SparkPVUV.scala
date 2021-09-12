package com.huangning.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.collection.immutable

/**
 * 背景：
 * PV是网站分析的一个术语，用以衡量网站用户访问的网页的数量。对于广告主，PV值可预期它可以带来多少广告收入。
 * 一般来说，PV与来访者的数量成正比，但是PV并不直接决定页面的真实来访者数量，如同一个来访者通过不断的刷新页面，也可以制造出非常高的PV
 *
 * 任务要求：
 * 1. 求各个网站的pv：PV（page view）即页面浏览量或点击量，是衡量一个网站或网页用户访问量,具体的说，PV值就是所有
 *    访问者在24小时（0点到24点）内看了某个网站多少个页面或某个网页多少次。PV是指页面刷新的次数，每一次页面刷新，就算做一次PV流量
 *
 *    度量方法就是从浏览器发出一个对网络服务器的请求（Request），网络服务器接到这个请求后，会将该请求对应的一个网页（Page）发送给
 *    浏览器，从而产生了一个PV。那么在这里只要是这个请求发送给了浏览器，无论这个页面是否完全打开（下载完成），那么都是应当计为1个PV。
 *
 * 2. 求各个网站的uv：UV（unique visitor）即独立访客数，指访问某个站点或点击某个网页的不同IP地址的人数在同一天内，
 *    UV只记录第一次进入网站的具有独立IP的访问者，在同一天内再次访问该网站则不计数。UV提供了一定时间内不同观众数量的
 *    统计指标，而没有反应出网站的全面活动。
 * 3. 求解每个网站下 不同城市的访问量，并且从大到小排序
 */
object SparkPVUV {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("test")
    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val path: String = "./data/pvuvdata"
    // 读入数据
    val lines: RDD[String] = sc.textFile(path)

    // 计算每个网址的每个地区的访问量
    val result: RDD[(String, immutable.Seq[(String, Int)])] = lines.map(line => {
      (line.split("\t")(5), line.split("\t")(1))
    }).groupByKey()
      .map(one => {
        val localmap: mutable.Map[String, Int] = mutable.Map[String, Int]()
        val site: String = one._1
        val iterator: Iterator[String] = one._2.iterator
        while (iterator.hasNext) {
          val local: String = iterator.next()
          if (localmap.contains(local)) {
            localmap.put(local, localmap(local) + 1)
          } else {
            localmap.put(local, 1)
          }
        }
        val tuples: List[(String, Int)] = localmap.toList.sortBy(one => {
          -one._2
        })
        if (tuples.size >= 3) {
//          (site, List(tuples.head, tuples(1), tuples(2)))
          //          val returnList = new ListBuffer[(String, Int)]()
          //          for(i <- 0 to 2){
          //            returnList.append(tuples(i))
          //          }

          val returnList: immutable.Seq[(String, Int)] = for (i <- 0 to 2) yield tuples(i)

          (site, returnList)
        } else {
          (site, tuples)
        }
      })
    result.foreach(println)

    // 计算pv值
//    lines.map(line => {
//      (line.split("\t")(5), 1)
//    }).reduceByKey((v1:Int, v2:Int) => {
//      v1 + v2
//    }).sortBy(tp => {
//      tp._2
//    }, ascending = false)
//      .foreach(println)

//    // 计算uv
//    lines.map(line => {
//      line.split("\t")(0) + "_" + line.split("\t")(5)
//    }).distinct()
//      .map( line => {
//        (line.split("_")(1), 1)
//      }).reduceByKey(_+_)
//      .sortBy(_._2, ascending = false)
//      .foreach(println)

  }
}
