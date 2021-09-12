package com.huangning.spark.ml

import org.apache.spark.ml.classification.{LogisticRegression, LogisticRegressionModel}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.param.ParamMap

/**
 * Author: 黄宁
 * Date: 2020/09/19
 * Function: 了解 TransFormer & Estimator
 * Note:
 * 1. Transformer
 *    Transformer是抽象类，它包含feature transformer和learned model
 *    1）feature transformer的原理是将读入的列map成新列feature vectors，然后将其追加到DataFrame
 *    2) learning model的原理是读入feature vectors列并预测标签，然后将其追加到DataFrame
 * 2. Estimator
 *    Estimator是可以拟合数据的各类算法的抽象类，它实现了.fit()，即接收DataFrame返回Model，这里的Model是transformer
 * 3. Parameters
 *    1) 有两种方法将参数传入算法
 *    法1. 针对算法的实例设定参数，比如针对LogisticRegression的实例lr设定参数lr.setMaxIter(10)，使得lr.fit()的时候迭代了10次
 *    法2. 将ParamMap传入fit()或者transform(). ParamMap中的任意参数都会覆盖原本设定的参数
 *    2) 参数只属于Estimators以及Transformers的实例，比如有两个LogisticRegression实例lr1和lr2，那么可以将参数构建成ParamMap(lr1.maxIter -> 10, lr2.maxIter -> 20).
 */

object TransEstAndParam {
  def main(args: Array[String]): Unit ={

    val spark: SparkSession = SparkSession.builder().appName("TransFormerAndEstimator").master("local").getOrCreate()
    spark.sparkContext.setLogLevel("Error")

    // 由tuple构成的Seq来作为训练集
    val training: DataFrame = spark.createDataFrame(Seq(
      (1.0, Vectors.dense(0.0, 1.1, 0.1)),
      (0.0, Vectors.dense(2.0, 1.0, -1.0)),
      (0.0, Vectors.dense(2.0, 1.3, 1.0)),
      (1.0, Vectors.dense(0.0, 1.2, -0.5))
    )).toDF("label", "features")

    // 创建一个LogisticRegression实例（Estimator）
    val lr: LogisticRegression = new LogisticRegression()

    // 通过explainParams打印参数文档
    println(s"LogisticRegression parameters:\n ${lr.explainParams()}\n")

    // 通过传递参数的法1来配置参数
    lr.setMaxIter(10)
      .setRegParam(0.01)

    // 使用之前设定的参数来学习模型(Transformer)
    val lr_clf: LogisticRegressionModel = lr.fit(training)

    // 通过.parent.extractParamMap查看fit期间的参数，打印的参数是name，value，其中names就是LogisticRegression实例的ID
    println(s"Model 1 was fit using parameters: ${lr_clf.parent.extractParamMap}")

    // 通过传递参数的法2来配置参数，可以指定一个参数，也可以同时指定多个参数，其中重复配置的参数会覆盖以前的参数
    val paramMap: ParamMap = ParamMap(lr.maxIter -> 20)
      .put(lr.maxIter, 30)
      .put(lr.regParam -> 0.1, lr.threshold -> 0.55)

    // 修改输出概率列的列名(ParamMaps可以相互结合)
    val paramMap2: ParamMap = ParamMap(lr.probabilityCol -> "myProbability")
    val paramMapCombined: ParamMap = paramMap ++ paramMap2

    // 将paramMapCombined传入fit中
    val model2: LogisticRegressionModel = lr.fit(training, paramMapCombined)
    println(s"Model 2 was fit using parameters: ${model2.parent.extractParamMap}")

    // 构建测试集
    val test: DataFrame = spark.createDataFrame(Seq(
      (1.0, Vectors.dense(-1.0, 1.5, 1.3)),
      (0.0, Vectors.dense(3.0, 2.0, -0.1)),
      (1.0, Vectors.dense(0.0, 2.2, -1.5))
    )).toDF("label", "features")

    // 预测的时候仅仅使用'features'列
    val res: Array[Row] = model2.transform(test)
      .select("features", "label", "myProbability", "prediction")
      .collect()

    res.foreach { case Row(features: Vector, label: Double, prob: Vector, prediction: Double) =>
        println(s"($features, $label) -> prob=$prob, prediction=$prediction")
      }
  }
}
