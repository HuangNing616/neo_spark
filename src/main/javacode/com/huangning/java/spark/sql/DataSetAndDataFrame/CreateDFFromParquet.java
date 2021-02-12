package com.huangning.java.spark.sql.DataSetAndDataFrame;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

public class CreateDFFromParquet {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("RDDWithReflection").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        Dataset<Row> jsonDF = sqlContext.read().format("json").load("./data/json");
        jsonDF.show();

        jsonDF.write().mode(SaveMode.Append).format("parquet").save("./sparksql/parquet");

        Dataset<Row> parquet = sqlContext.read().format("parquet").load("./sparksql/parquet");
        parquet.show();

    }
}
