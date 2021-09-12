package com.huangning.spark.sql.dataframe

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/06/15
 * Target: 读取hive本地仓库中的数据
 */
object OperatorHiveOnLocal {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("CreateDataFrameFromHive")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

    spark.sparkContext.setLogLevel("Error")

//    spark.sql("show databases")
//    spark.sql("use spark")
//    spark.sql("show tables")
//    spark.sql("drop table if exists student_infos")
//    spark.sql("create table if not exists student_infos(name string, age int) row format delimited fields terminated by '\t'")
//    spark.sql("load data local inpath './data/student_infos' into table student_infos")
//    spark.sql("drop table if exists student_scores")
//    spark.sql("create table if not exists student_scores ( name string, score int) row format delimited fields terminated by '\t'")
//    spark.sql("load data local inpath './data/student_scores' into table student_scores")
//    val result: DataFrame = spark.sql("select si.name, si.age, ss.score from student_infos si, student_scores ss where si.name = ss.name")
//    spark.sql("drop table if exists good_student_infos")
//    // 将数据框保存带hive表中
//    result.write.mode(SaveMode.Overwrite).saveAsTable("good_student_infos")

//    // 创建一个具有多种数据格式的hive表
//    spark.sql("drop table if exists t")
//    spark.sql("create table if not exists t(id struct<id1:int,id2:int,id3:int>,name array<string>,xx map<int,string>) " +
//      " row format delimited " +
//      "fields terminated by ' ' " +
//      "collection items terminated by ',' " +
//      "map keys terminated by ':' " +
//      "lines terminated by '\n'")
//    spark.sql("load data local inpath './data/student_t' overwrite into table t")
//    val result2: DataFrame = spark.sql("select * from t")
//    result2.show(truncate = false)
//    result2.write.mode(SaveMode.Overwrite).saveAsTable("t_result")
  }
}
