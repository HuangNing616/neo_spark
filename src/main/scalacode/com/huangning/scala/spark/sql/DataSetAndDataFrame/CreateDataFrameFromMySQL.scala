package com.huangning.scala.spark.sql.DataSetAndDataFrame

import java.util.Properties

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, DataFrameReader, SaveMode, SparkSession}

/**
 * Author:Henry
 * Date:2020-05-14
 * Function: 从数据库中读取DataFrame
 */
object CreateDataFrameFromMySQL {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder()
      .master("local")
      .appName("CreateDataFrameFromMySQL")
      .config("spark.sql.shuffle.partitions", 1) // 可以将默认的200个分区变成1个分区，任务少的时候加快运行时间
      .getOrCreate()
    //    spark.sparkContext.setLogLevel("Error")

    /**
     * 读取mysql表的第一种方式
     */
//    val properties = new Properties()
//    properties.setProperty("user", "root")
//    properties.setProperty("password", "11111111")

//    //默认会用到mysql-connector-java里面的包
//    val person: DataFrame = spark.read.jdbc("jdbc:mysql://localhost:3306/spark", "person", properties)
//    person.show()
//
//    /**
//     * 读取mysql表的第二种方式
//     * 相比上一种方法可以显式指出来用了哪种包
//     * 键是指定写法，不能修改
//     */
//    val map: Map[String, String] = Map[String, String](
//      "url" -> "jdbc:mysql://localhost:3306/spark",
//      "driver"->"com.mysql.jdbc.Driver",
//      "user"->"root",
//      "password"->"11111111",
//      "dbtable"->"score" //表名字
//    )
//
//    val score: DataFrame = spark.read.format("jdbc").options(map).load()
//    score.show()

//    /**
//     * 读取mysql表的第三种方式
//     */
//    val reader: DataFrameReader = spark.read.format("jdbc")
//      .option("url", "jdbc:mysql://localhost:3306/spark")
//      .option("driver", "com.mysql.jdbc.Driver")
//      .option("user", "root")
//      .option("password", "11111111")
//      .option("dbtable", "score")
//
//
//    val score2: DataFrame = reader.load()
//    score2.show()

    /**
     * 读取mysql表的第四种方式
     */
//    spark.read.jdbc("jdbc:mysql://localhost:3306/spark",
//      "(select person.id,person.name,person.age,score.score from person ,score where  person.id = score.id) T",
//      properties).show()

//    // 将以上两张表注册临时表，关联查询
//    person.createOrReplaceTempView("person")
//    score.createOrReplaceTempView("score")
//
//
//    // 将查询结果保存在Mysql中
//    val result: DataFrame = spark.sql("select person.id,person.name,person.age,score.score from person ,score where  person.id = score.id")
//    result.write.mode(SaveMode.Overwrite).jdbc("jdbc:mysql://localhost:3306/spark", "result", properties)
  }
}
