package com.huangning.scala.spark.sql.DataSetAndDataFrame

import org.apache.spark.sql.{Dataset, SparkSession}
import scala.collection.immutable

case class Student(name:String,age:Long)
case class Person(id:Int, name:String, age:Int, score:Double)

/**
 * Author: huangning
 * Date: 2020/09/12
 * Target: Creating Datasets
 * Note: Datasets are similar to RDDs, however, instead of using Java serialization or Kryo they use a specialized
 * Encoder to serialize the objects for processing or transmitting over the network. While both encoders and
 * standard serialization are responsible for turning an object into bytes, encoders are code generated
 * dynamically and use a format that allows Spark to perform many operations like filtering, sorting
 * and hashing without deserializing the bytes back into an object.
  */
object CreateDataSet {
  def main(args: Array[String]): Unit = {

    // 创建spark对象
    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("createStruceDataSet")
      .getOrCreate()
    import spark.implicits._

    // 方法1: 根据scala集合创建DataSet
    val list: Seq[Person] = Seq[Person](
      Person(1, "zhangsan", 18, 100),
      Person(2, "lisi", 19, 200),
      Person(3, "wangwu", 20, 300)
    )

    // 将List映射成Person类型的DataSet
    val ds1: Dataset[Person] = list.toDS()

    // 将List映射成Int类型的DataSet
    // 默认列名是value，如果是多列的话默认列名是`_1、_2、_3...`。
    val ds2: Dataset[Int] = List[Int](1, 2, 3, 4, 5).toDS()

    // 方法2：由json文件和类直接映射成DataSet
    val ds3: Dataset[Student] = spark.read.json("./data/json").as[Student]

    // 方法3：由文本文件加载DataSet
    val ds4: Dataset[String] = spark.read.textFile("./data/people.txt")
    val ds5: Dataset[Person] = ds4.map((row: String) => {
      val arr: Array[String] = row.split(",")
      Person(arr(0).toInt, arr(1), arr(2).toInt, arr(3).toDouble)
    })
  }
}
