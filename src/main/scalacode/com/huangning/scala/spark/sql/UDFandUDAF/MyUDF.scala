package com.huangning.scala.spark.sql.UDFandUDAF

import org.apache.spark.sql.{DataFrame, SparkSession, functions}
import org.apache.spark.sql.expressions.UserDefinedFunction

/**
 * Author: 黄宁
 * Date: 2021/02/12
 * Func: 了解UDF的两种使用方法
 */
object MyUDF {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("UDF")
      .getOrCreate()
    import spark.implicits._
    val df: DataFrame = spark.read.json("/Users/huangning/HNCode/neo_spark/data/student_scores.json")
    val df2: DataFrame = List[String]("zhangsan", "lisi", "wangwu", "zhaoliu", "tianqi").toDF("name")

    /**
     * Spark DataFrame UDF
     * 第一步: 注册
     * 第二步: 使用
     */
    val avgScoreUDF: UserDefinedFunction = functions.udf[Double,Int,Int,Int](avgScorePerStudent)
    df.withColumn("avgScore", avgScoreUDF($"language",$"math",$"english")).show(false)

    /**
     * SQL DataFrame UDF
     * 第一步: 创建视图
     * 第二步: 注册
     * 第三步: 使用
     */
    df.createOrReplaceTempView("tmp_student_scores")
    spark.udf.register("avgScore", functions.udf[Double,Int,Int,Int](avgScorePerStudent))
    spark.sql("select *, avgScore(language,math,english) as avgScore from tmp_student_scores").show(false)

    //还可以使用匿名函数代替functions.udf
    df2.createOrReplaceTempView("students")
    spark.udf.register("strLen", (n:String)=>{
      n.length
    })
    spark.sql("select name, strLen(name) as length from students sort by length desc").show(false)
  }

  // 计算每个同学的平均成绩
  def avgScorePerStudent(language:Int,math:Int,english:Int):Double={
    ((language+math+english)/3.0).formatted("%.2f").toDouble
  }
}
