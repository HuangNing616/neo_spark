package com.huangning.spark.sql.dataframe

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Row, RowFactory, SparkSession}

/**
 * Author: 黄宁
 * Date:  2020/06/15
 * Target: 通过动态创建Schema的方式加载DataFrame
 * Note：动态创建的ROW中数据的顺序要与Schema中的数据保持一致
 */
object CreateDataFrameFromRDDWithSchema {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local").appName("createdataframefromrddwithschema").getOrCreate()
    val peopleRDD: RDD[String] = spark.sparkContext.textFile("./data/people.txt")

    // 将peopleRDD转换成RDD[Row]
    val rowRDD: RDD[Row] = peopleRDD.map(line => {
      val arr: Array[String] = line.split(",")
      Row(arr(2).toInt, arr(1), arr(0).toInt, arr(3).toLong)
    })

    // 可以动态地里面塞入列
    val structType: StructType = StructType(List[StructField](
      StructField("id", IntegerType, nullable = true),
      StructField("name", StringType, nullable = true),
      StructField("age", IntegerType, nullable = true),
      StructField("score", LongType, nullable = true)
    ))

    // createDataFrame将二者自动映射
    val frame: DataFrame = spark.createDataFrame(rowRDD, structType)
    frame.show()
    frame.printSchema()
    frame.createOrReplaceTempView("people")

    //    val schemaString = "id name age score"
    //    /**
    //      * 动态创建Schema方式
    //      */
    //    val fields: ArrayNote[StructField] = schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, nullable = true))
    //    val schema: StructType = StructType(fields)
    //
    //    val rowRDD: RDD[Row] = peopleRDD
    //      .map(_.split(","))
    //      .map(attributes => Row(attributes(0).trim, attributes(1).trim, attributes(2).trim, attributes(3).trim))

    import spark.implicits._
    val results: DataFrame = spark.sql("SELECT name FROM people")
    results.map(teenager => "Name: " + teenager(0)).as("myCol").show()

  }
}
