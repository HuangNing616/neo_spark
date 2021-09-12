package com.huangning.spark.sql.dataframe

import org.apache
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object CreateDataFrameFromParquet {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local")
      .appName("CreateDataFrameFromParquet")
      .getOrCreate()


//    val df1: DataFrame = spark.read.json("./data/json")
    val df1: DataFrame = spark.read.format("json").load("./data/json")
//    df1.show()

    /**
     * 保存成parquet文件
     */
//    df1.write.mode(SaveMode.Append).format("parquet").save("./data/parquet")
    df1.write.mode(SaveMode.Append).parquet("./data/parquet")

    /**
     * 读取parquet文件
     */
//    val df2: DataFrame = spark.read.parquet("./data/parquet")
    val df2: DataFrame = spark.read.format("parquet").load("./data/parquet")
    val count: Long = df2.count()
    df2.show(100)
    println(count)
  }
}
