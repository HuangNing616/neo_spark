package com.huangning.spark.ml

import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.ml.linalg.Vector


/**
 * Author: 黄宁
 * Date: 2020/09/19
 * Function: 了解Pipeline用法
 * Notes:
 * 1. Pipeline是由一系列的PipelineStages构成，其中每个stage要么是Transformer，要么是Estimator
 * 2. 输入DataFrame进入Pipeline后按照顺序被传给每个stage，在transformer stage中，transform()方法被调用在DataFrame，
 *    在Estimator的stage中，fit()方法被调用产生transformer，并称之为PipelineModel
 * 3. Pipeline是一个Estimator，在执行fit()之后产生PipelineModel并用在预测环节，PipelineModel和初始的Pipeline有相同数量的stage,
 *    但初始的Pipeline中的所有Estimators在PipelineModel中都被转成了Transformer
 */
object Pipeline {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().appName("Pipeline").master("local").getOrCreate()

    // 用id,text以及label的tuple构成的Sequence作为训练documents
    val training: DataFrame = spark.createDataFrame(Seq(
      (0L, "a b c d e spark", 1.0),
      (1L, "b d", 0.0),
      (2L, "spark f g h", 1.0),
      (3L, "hadoop mapreduce", 0.0)
    )).toDF("id", "text", "label")

    // 构建由tokenizer, hashingTF, and lr构成的pipeline，
    val tokenizer: Tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")

    // setBinary:
    //    true(伯努利计算), 统计某词在一篇文章中只要出现了就为1，否则为0
    //    false(多项式分布计算，默认)，一个词在一篇文章中出现多少次，计算多少次
    // setNumFeatures: 设计词表的大小
    // HashingTF将文档的每行转换成(词表大小, 词的id<单个字符时同ascii码一样>, 词频)形式，其中词频指的是当前行(文章的词频)
    val hashingTF: HashingTF = new HashingTF()
      .setBinary(true)
      .setNumFeatures(1000)
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")

    val lr: LogisticRegression = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.001)

    val pipeline: Pipeline = new Pipeline()
      .setStages(Array(tokenizer, hashingTF, lr))

    val model: PipelineModel = pipeline.fit(training)

    // 将拟合的模型保存(Transformer)
    model.write.overwrite().save("/Users/bytedance/ByteCode/SparkLab/data/spark-logistic-regression-model")

    // 将未拟合的模型保存(Estimator)
    pipeline.write.overwrite().save("/Users/bytedance/ByteCode/SparkLab/data/unfit-lr-model")

    // 加载保存的模型
    val sameModel: PipelineModel = PipelineModel.load("/Users/bytedance/ByteCode/SparkLab/data/spark-logistic-regression-model")

    // 用id, text但是没有label的tuple构成的Sequence作为测试documents
    val test: DataFrame = spark.createDataFrame(Seq(
      (4L, "spark i j k"),
      (5L, "l m n"),
      (6L, "spark hadoop spark"),
      (7L, "apache hadoop")
    )).toDF("id", "text")

    val res: Array[Row] = sameModel.transform(test)
      .select("id", "text", "probability", "prediction")
      .collect()

      res.foreach { case Row(id: Long, text: String, prob: Vector, prediction: Double) =>
        println(s"($id, $text) --> prob=$prob, prediction=$prediction")
      }
  }
}
