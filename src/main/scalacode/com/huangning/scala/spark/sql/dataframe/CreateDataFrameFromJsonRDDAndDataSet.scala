package com.huangning.scala.spark.sql.dataframe

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/06/15
 * Target: 将json格式的RDD和DataSet转换成DataFrame
 * Note: 加载成的DataFrame会自动按照列的Ascii码排序
 */

object CreateDataFrameFromJsonRDDAndDataSet {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().appName("createDFFromJsonRDD").master("local").getOrCreate()
    val jsonList: Seq[String] = List[String](
      "{\"name\":\"zhangsan\",\"age\":20}",
      "{\"name\":\"lisi\",\"age\":21}",
      "{\"name\":\"wangwu\",\"age\":22}"
    )
    val jsonList2: Seq[String] = List[String](
      "{\"name\":\"zhangsan\",\"score\":100}",
      "{\"name\":\"lisi\",\"score\":200}",
      "{\"name\":\"wangwu\",\"score\":300}"
    )

    /**
      * 1.6版本方式
      */
    val jsonRDD: RDD[String] = spark.sparkContext.makeRDD(jsonList)
    val df1: DataFrame = spark.read.json(jsonRDD)

    /**
      * 1.6+版本
      */
    import spark.implicits._
    val jsonDs: Dataset[String] = jsonList.toDS()
    val scoreDs: Dataset[String] = jsonList2.toDS()
    val df2: DataFrame = spark.read.json(jsonDs)
    val df3 :DataFrame = spark.read.json(scoreDs)

    df1.show(100)
    df2.show(100)
    df3.show(100)
  }
}
