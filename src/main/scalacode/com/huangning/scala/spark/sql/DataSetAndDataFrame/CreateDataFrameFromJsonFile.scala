package com.huangning.scala.spark.sql.DataSetAndDataFrame

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Column, DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.functions.col


/**
 * Author:  黄宁
 * Date: 2020/05/08
 * Target:读取json格式的文件加载DataFrame
 * Note：
 * 1. json文件得到的dataFrame会按照列的ascii码排序
 * 2. scala和java的数组是一样的，需要遍历/通过toBuffer可以直接打印出来
 * 3. java.lang.Long cannot be cast to java.lang.Integer
 */
object CreateDataFrameFromJsonFile {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .appName("DataSetFromJsonFileAndNestJsonFileAndJsonArray")
      .master("local")
      .getOrCreate()

    // 读取一般的json文件
    val df1: DataFrame = spark.read.json("./data/json")
//    val df1: DataFrame = spark.read.format("json").load("./data/json")
//    df1.printSchema()
//    df1.show(5)
//    df1.createOrReplaceTempView("infosView")
//    spark.sql("select * from infosView").show(100)

//    //读取嵌套的json文件
//    val df2: DataFrame = spark.read.format("json").load("./data/NestJsonFile")
//    df2.printSchema()
//    df2.createOrReplaceTempView("infosView")
//    spark.sql("select name,infos.age,score,infos.gender from infosView").show(100, truncate = false)

//    import org.apache.spark.sql.functions._
//    import spark.implicits._
//
//    // 读取Array格式的json文件
//    val df3: DataFrame = spark.read.json("./data/jsonArrayFile")
//    df3.printSchema()
//    val transDF: DataFrame = df3.select($"name", $"age", explode($"scores")).toDF("name", "age","allScores")
//    transDF.printSchema()
//
//    transDF.select($"name",$"age",
//      $"allScores.yuwen" as "yuwen",
//      $"allScores.shuxue" as "shuxue",
//      $"allScores.yingyu" as "yingyu").show(200)

  }
}
