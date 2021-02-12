package com.huangning.scala.spark.sql.UDFandUDAF

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/06/15
 * Target: 创建用户自定义函数
 */
object UDF {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("UDF")
      .getOrCreate()
    import spark.implicits._

    val nameList: List[String] = List[String]("zhangsan", "lisi", "wangwu", "zhaoliu", "tianqi")
    val nameDF: DataFrame = nameList.toDF("name")
    nameDF.createOrReplaceTempView("students")

    spark.udf.register("STRLEN", (n:String)=>{
      n.length
    })
    spark.sql("select name, STRLEN(name) as length from students sort by length desc").show(100)

  }
}
