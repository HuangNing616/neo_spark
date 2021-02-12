package com.huangning.scala.spark.ml.FeatureExtractors

import org.apache.spark.ml.feature.{HashingTF, IDF, IDFModel, Tokenizer}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * Author: 黄宁
 * Date: 2020/09/19
 * Function: 了解TF-IDF用法
 */
object TFIDF {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().appName("TF-IDF").master("local").getOrCreate()

    val sentenceData: DataFrame = spark.createDataFrame(Seq(
      (0.0, "Hi I heard about Spark"),
      (0.0, "I wish Java could use case classes"),
      (1.0, "Logistic regression models are neat")
    )).toDF("label", "sentence")

    // tokenizer(transformer)将每个句子分割成单词列表作为新列，追加到原来的DataFrame
    val tokenizer: Tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
    val wordsData: DataFrame = tokenizer.transform(sentenceData)

    // HashingTF(transformer)将words哈希成feature vector
    // 可能会有hash冲突，也就是多个单词对应的index是一样的
    val hashingTF: HashingTF = new HashingTF()
      .setBinary(false)
      .setInputCol("words")
      .setOutputCol("rawFeatures").
      setNumFeatures(20)
    val featurizedData: DataFrame = hashingTF.transform(wordsData)
    featurizedData.show(5, truncate=false)

    // IDF(Estimator)拟合fit在dataset并且产生一个idfModel
    val idf: IDF = new IDF().setInputCol("rawFeatures").setOutputCol("features")
    val idfModel: IDFModel = idf.fit(featurizedData)

    val rescaledData: DataFrame = idfModel.transform(featurizedData)
    rescaledData.select("label", "features").show(truncate = false)

    rescaledData.write
      .mode(SaveMode.Overwrite)
      .csv("/Users/bytedance/ByteCode/SparkLab/data/tfdata.csv")
  }
}
