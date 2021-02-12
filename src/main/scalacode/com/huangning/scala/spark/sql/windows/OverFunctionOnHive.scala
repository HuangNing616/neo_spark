package com.huangning.scala.spark.sql.windows

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/07/05
 * Target: 了解 overFunction(开窗函数)
 * row_number() over (partition by xx order by xx) as rank
 * Note: 定义别名：columnA as X 不需要使用单引号
 */
object OverFunctionOnHive {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("OverFunctionOnHive")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

    spark.sparkContext.setLogLevel("Error")

    spark.sql("use spark")
    spark.sql("drop table if exists sales")
    spark.sql("create table if not exists sales (date string, category string, price Int)" + "row format delimited fields terminated by '\t'")
    spark.sql("load data local inpath './data/sales' into table sales" )

    /**
     * rank 在每个组内从1开始
     * 5 A 200 -- 1
     * 3 A 100 -- 2
     * 4 A 80  -- 3
     * 7 A 60  -- 4
     *
     * 1 B 100 -- 1
     * 8 B 90  -- 2
     * 6 B 80  -- 3
     * 1 B 70  -- 4
     */
    val result: DataFrame = spark.sql(
      "select "
        + "date, category, price "
        + "from ("
          + "select "
             + "date, category, price, row_number() over (partition by category order by price desc) rank "
          + "from sales) t "
        + "where rank <= 3"
    )

    //将结果保存到hive
    result.write.mode(SaveMode.Append).saveAsTable("saleResult")

    //读取保存到hive中的sales
    val salesDF: DataFrame = spark.sql("select * from saleResult")
    salesDF.show(100)
  }
}
