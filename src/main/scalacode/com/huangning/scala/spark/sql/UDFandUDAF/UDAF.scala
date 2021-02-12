package com.huangning.scala.spark.sql.UDFandUDAF

import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DataTypes, IntegerType, StringType, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/07/04
 * Target: 了解UDAF(UserDefinedAggregateFunction 用户自定义聚合函数 抽象类）
 * Note:
 * 1. 常用的聚合函数有count, avg, sum, min, max等等
 * 2. 特点：多对一
 * 3. 聚合函数必须和group by一起使用
 * 4. 和聚合函数一起出现的字段，必须要出新在group by的后面，比如：select name ,count(*) from table group by name
 */
class MyUDAF extends UserDefinedAggregateFunction{
  // 指定输入数据类型
  def inputSchema: StructType ={
    DataTypes.createStructType(Array(DataTypes.createStructField("uuuu", StringType, true)))
  }

  /**
   * 为每个分组的数据执行初始化
   * 两个部分初始化
   * 1. 在map端, 每个RDD分区内按照groupby的分组，每个分组都有个初始化的值
   * 2. 在reduce端给每个groupby分组的一个初始值
   */
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0
  }

  // 每个组，有新的值进来的时候，进行分组对应的聚合值的计算 重要！！！
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getAs[Int](0) + 1
  }

  /**
   * 最后merge的时候，在各个节点上的聚合值，要进行merge（也就是合并）
   * @param buffer1: reduce端给每个groupby分组的一个初始值
   * @param buffer2: 每个分区的每个groupby的结果
   */
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getAs[Int](0) + buffer2.getAs[Int](0)
  }

  // 聚合操作的时候，所处理的数据类型
  override def bufferSchema: StructType = {
    DataTypes.createStructType(Array(DataTypes.createStructField("QQQQ", IntegerType, true)))
  }

  // 最后返回一个最终的聚合值要和dataType的类型一一对应
  override def evaluate(buffer: Row): Any = {
    buffer.getAs[Int](0)
  }

  // 最终函数返回值的类型
  override def dataType: DataType = {
    DataTypes.IntegerType
  }

  // 多次运行 相同的输入总是相同的输出，确保一致性
  override def deterministic: Boolean = {
    true
  }
}

object UDAF {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().appName("UDAF").master("local").getOrCreate()
    val nameList: List[String] = List[String]("zhangsan", "lisi", "wangwu", "zhaoliu", "zhangsan", "lisi", "zhangsan")

    // 将scala的List转成DataFrame
    import spark.implicits._
    val frame: DataFrame = nameList.toDF("name")
    frame.createOrReplaceTempView("students")

    // 注册UDAF函数
    spark.udf.register("NAMECOUNT", new MyUDAF())
    spark.sql("select name, NAMECOUNT(name) from students group by name").show(100)

  }
}
