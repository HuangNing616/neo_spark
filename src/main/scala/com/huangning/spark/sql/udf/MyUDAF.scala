package com.huangning.spark.sql.udf

import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, IntegerType, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/07/04
 * Func: 了解UDAF的两种使用方法
 * Note:
 * 1. 目前存在DataFrame以及SQL的UDF
 * 2. UDF使用步骤为注册+使用
 */
object MyUDAF {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("UDF")
      .getOrCreate()
    import spark.implicits._
    val df: DataFrame = spark.read.json("/Users/huangning/HNCode/neo_spark/data/student_scores.json")

    /** Spark DataFrame UDAF */
    val avgScore: MyUDAF = new MyUDAF()
    df.groupBy($"departmentId",$"classId")
      .agg(avgScore($"language").as("avgScorePerClass"))
      .show(false)

    /** SQL DataFrame UDAF */
    df.createOrReplaceTempView("student_scores")
    //UDAF注册
    spark.udf.register("avgScore",new MyUDAF())
    //UDAF使用
    spark.sql("select departmentId,classId,avgScore(language) as avgScore from student_scores group by departmentId,classId")
      .show(false)
  }
}

//按照departmentId以及classId分组后求语文(language)的平均成绩
class MyUDAF extends UserDefinedAggregateFunction{

  // 输入数据类型
  override def inputSchema: StructType = {
    new StructType().add("subject",IntegerType)
  }

  // buffer数据类型:
  override def bufferSchema: StructType = {
    //分别统计每个分组下的总成绩以及总人数
    new StructType()
      .add("total_grade",IntegerType)
      .add("count_people",IntegerType)
  }

  //返回值类型
  override def dataType: DataType = DoubleType

  //如果相同输入有相同的返回结果
  override def deterministic: Boolean = true

  //buffer数据初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0)=0
    buffer(1)=0
  }

  //更新buffer值
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0)=buffer.getAs[Int](0)+input.getAs[Int](0)
    buffer(1)=buffer.getAs[Int](1)+1
  }

  //合并buffer值
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getAs[Int](0) + buffer2.getAs[Int](0)
    buffer1(1) = buffer1.getAs[Int](1) + buffer2.getAs[Int](1)
  }

  //计算最终结果
  override def evaluate(buffer: Row): Any = {
    buffer.getAs[Int](0).toDouble/buffer.getAs[Int](1)
  }
}
