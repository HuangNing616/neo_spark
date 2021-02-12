package com.huangning.scala.spark.ml

import org.apache.spark.ml.linalg
import org.apache.spark.ml.linalg.{Matrix, Vector, Vectors}
import org.apache.spark.ml.stat.{ChiSquareTest, Correlation}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
//

/**
 * Author: 黄宁
 * Date: 2020/09/05
 * Function: 熟悉基本的统计计算方法, 比如皮尔逊相关系数以及斯皮尔曼相关系数，皮尔逊的卡方检验
 * Note:
 * 1. dense向量是普通的double数组，而sparse向量是由两个并列的数组indices和values组成
 * 2. 计算Person相关系数的变量必须要满足连续性，接近单峰分布以及每对观测指相互独立
 * 3. 卡方检验要满足特征和标签是类别变量
 */

object BasicStatistics {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder().appName("BasicStatistics").master("local").getOrCreate()
    spark.sparkContext.setLogLevel("Error")
    import spark.implicits._

    val data: Seq[linalg.Vector] = Seq(
      Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
      Vectors.dense(4.0, 5.0, 0.0, 3.0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0),
      Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
    )

    // 两种相关系数的计算方法
    val df: DataFrame = data.map(Tuple1.apply).toDF("features")
    val Row(coeff1: Matrix): Row = Correlation.corr(df, "features").head
    println(s"Pearson correlation matrix:\n $coeff1, ${coeff1.getClass}")

    val Row(coeff2: Matrix): Row = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2")

    // Pearson’s Chi-squared ( χ2) tests for independence
    val data2: Seq[(Double, linalg.Vector)] = Seq(
      (0.0, Vectors.dense(0.5, 10.0)),
      (0.0, Vectors.dense(1.5, 20.0)),
      (1.0, Vectors.dense(1.5, 30.0)),
      (0.0, Vectors.dense(3.5, 30.0)),
      (0.0, Vectors.dense(3.5, 40.0)),
      (1.0, Vectors.dense(3.5, 40.0))
    )

    val df2: DataFrame = data2.toDF("label", "features")
    val chi: Row = ChiSquareTest.test(df2, "features", "label").head

    println(s"pValues = ${chi.getAs[Vector](0)}")
    println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
    println(s"statistics ${chi.getAs[Vector](2)}")

  }
}
