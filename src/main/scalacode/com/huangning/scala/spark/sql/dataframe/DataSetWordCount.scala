package com.huangning.scala.spark.sql.dataframe

import org.apache.spark.sql._
import org.apache.spark.sql.functions._

/**
 * Author: 黄宁
 * Date: 2020/06/15
 * Target: 利用DateSet的API来实现wordcount
 * Note:
 * 导入org.apache.spark.sql.functions._，来使用dataset中groupBy中的agg里面的count函数
 */

object DataSetWordCount {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("createDataSet")
      .getOrCreate()
    spark.sparkContext.setLogLevel("Error")
    import spark.implicits._

    val linesDs: Dataset[String] = spark.read.textFile("./data/words").as[String]
    val words: Dataset[String] = linesDs.flatMap(line=>{line.split(" ")})

    // 统计每个单词的个数并从大到小排序
    val groupDs: RelationalGroupedDataset = words.groupBy($"value" as "word")
    val aggDs: DataFrame = groupDs.agg(count("*") as "totalCount")
    val result: Dataset[Row] = aggDs.sort($"totalCount" desc)
    result.show(100, truncate = false)

    // 通过sparkSQL语句统计每个单词的个数并从大到小排序
    val frame: DataFrame = words.withColumnRenamed("value","word")
    frame.createOrReplaceTempView("myWords")
    spark.sql("select word,count(word) as totalCount from myWords group by word order by totalCount desc").show()
  }
}
