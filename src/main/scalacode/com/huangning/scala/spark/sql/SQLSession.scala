package com.huangning.scala.spark.sql

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * Author: 黄宁
 * Date: 2020/02/12
 * Func: 多Session创建视图
 * Note:
 * 1. 一个Spark App只能有一个context
 * 2. 在同一个Spark Session中，后者创建的视图会覆盖前者
 * 3. 保留多个视图可以通过创建多个Session的方法
 * 4. 全局视图GlobalTempView可以跨Session访问
 * 5. 同一个Driver可以有多个Session，但是彼此的TempView是看不见的
 */
object SQLSession {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder()
      .appName("SQLSession")
      .master("local")
      .getOrCreate()

    val df: DataFrame = spark.read.format("json")
      .load("/Users/huangning/HNCode/neo_spark/data/student_scores.json")

    // 创建局部视图
    df.createTempView("t1")

    // 创建全局视图
    df.createGlobalTempView("t2")
    val df1: DataFrame = spark.sql("select * from t1")
    df1.show()

    // 创建新的会话
    val session: SparkSession = spark.newSession()
    session.sql("select * from global_temp.t2").show()

  }
}
