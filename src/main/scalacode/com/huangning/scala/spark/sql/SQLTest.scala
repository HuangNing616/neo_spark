package com.huangning.scala.spark.sql

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * 一个spark app 只能有一个sparkcontext
 * getOrCreate() 是往前找spark context对象，如果有就用存在的，如果没有就创建一个新的
 * 用多个session 的情景：如果第一个session创建一个TempView，但是再创建一个t1会将之前覆盖，所以要用到
 * 第二个session，或者第一个session的sql非常复杂，所以需要一个新的session来搞，一般就是一个session
 */
object SQLTest {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("test").setMaster("local")

    val sc = new SparkContext(conf)
    //    val sqlContext = new SQLContext(sc = sc)

    // 创建一个sparkSesson对象
    val spark: SparkSession = SparkSession.builder().appName("test").master("local").getOrCreate()


//    val df: DataFrame = spark.read.format("json").load("./data/json")
    val df: DataFrame = spark.read.json("./data/json")
    //为了写sql语句，所以首先要注册成表（spark 1.6 又一个registerTempTable）
    // t1本质上不是表，而是指向df的指针，相当于数据库中的试图
//    df.registerTempTable("t1")

    df.createTempView("t1")
//    df.createTempView("t1")

    // 有多个session的时候会用到global,也就是
//    df.createOrReplaceGlobalTempView("t1")
    df.createGlobalTempView("t2")
    val df1: DataFrame = spark.sql("select name, age from t1")
    df1.show()

    // 虽然有两个会话，但是公用同一个sparkcontext, 所以说在同一个driver中可以有多个session，彼此的表看不见
    val session: SparkSession = spark.newSession()
    session.sql("select * from global_temp.t2").show()



    ///////////下面是dataframe api的方法///////////////
    //    df.printSchema()
//    df.show(100)
//    val rows: Array[Row] = df.take(10)
//    val rdd: RDD[Row] = df.rdd
//    rdd.foreach(row =>{
////      val name: String = row.getAs[String](1)
////      val age: Long = row.getAs[Long](0)
//      val name: String = row.getAs[String]("name")
//      val age: Long = row.getAs[Long]("age")
//      println(s"name =  $name , age = $age")
//    })

    // select name , age from t
//    df.select("name", "age").show()

    // select name , age from t where age > 18
//    val df1: Dataset[Row] = df.select(df.col("name")).filter("age>18")
//    df1.show()

    //这些都是dataframe原生api的程序
//    df.select("name", "age").filter("name like 'zhangsan%'").show()


  }
}
