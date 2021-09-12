package com.huangning.spark.sql.windows

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/07/05
 * Target: 了解 overFunction(开窗函数)
 * row_number() over (partition by xx order by xx) as rank
 */
object OverFunctionOnMySQL {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("OverFunctionOnMySQL")
      .master("local")
      .getOrCreate()

    val properties = new Properties()
    properties.setProperty("user", "root")
    properties.setProperty("password", "11111111")

    val sql: String = "select * from (select date, category, price, row_number() over (partition by category order by price desc) 'rank' from sales) t where t.rank <= 3"

    val person: DataFrame = spark.read.jdbc("jdbc:mysql://localhost:3306/spark", s"($sql)T", properties)
    person.show(100, truncate = false)
  }
}
