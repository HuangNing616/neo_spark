package com.huangning.java.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

/**
 * 实现java版本的sparkSQL
 *
 */
public class SQLTest {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local").setAppName("SQLTest");
        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

//        Dataset<Row> df = sqlContext.read().json("./data/json");
        Dataset<Row> df = sqlContext.read().format("json").load("./data/json");
        df.show(100);
        df.printSchema();

        // 将df转化程rdd
        JavaRDD<Row> rowJavaRDD = df.javaRDD();
        rowJavaRDD.foreach(new VoidFunction<Row>() {
            @Override
            public void call(Row row) throws Exception {
                System.out.println(row);
//                String name = row.getAs("name");
//                long age = row.getAs("age");
//                System.out.println("name = " + name + "age = " + age);
            }
        });
//        df.select(df.col("name")).show();
//        df.registerTempTable("table1");
//
//        Dataset<Row> sql = sqlContext.sql("select * from table1");
//        sql.show();
    }
}
