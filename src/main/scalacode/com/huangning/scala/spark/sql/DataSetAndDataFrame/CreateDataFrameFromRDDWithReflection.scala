package com.huangning.scala.spark.sql.DataSetAndDataFrame

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession, DataFrame}


/**
 * Author:Henry
 * Date:2020-05-12
 * Target: 通过反射的方式将RDD转换成DataFrame, 然后用sql去查询
 */
object CreateDataFrameFromRDDWithReflection {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder()
      .master("local")
      .appName("Reflection")
      .getOrCreate()

    spark.sparkContext.setLogLevel("Error")

    /**
     * 直接读取文件为DataSet
     */
//    val person: Dataset[String] = spark.read.textFile("./data/people.txt")
//    person.map( one => {
//      val arr = one.split(",")
//      Person(arr(0).toInt, arr(1).toString, arr(2).toInt, arr(3).toDouble)
//    })

    /**
     * 直接读取文件为RDD
     */
    val rdd: RDD[String] = spark.sparkContext.textFile("./data/people.txt")
    val personRDD: RDD[Person] = rdd.map(one => {
      val arr: Array[String] = one.split(",")
      Person(arr(0).toInt, arr(1).toString, arr(2).toInt, arr(3).toDouble)
    })

    import spark.implicits._
    val frame: DataFrame = personRDD.toDF()
    frame.show()

  }
}
