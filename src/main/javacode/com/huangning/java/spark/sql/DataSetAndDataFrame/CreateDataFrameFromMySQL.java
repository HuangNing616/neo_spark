package com.huangning.java.spark.sql.DataSetAndDataFrame;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CreateDataFrameFromMySQL {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local").setAppName("CreateDataFrameFromMySQL").set("spark.sql.shuffle.partitions", "1");
        SparkContext sc = new SparkContext(conf);
        sc.setLogLevel("Error");
        SQLContext sqlContext = new SQLContext(sc);

        /**
         * 读取数据库的第一种方法
         */
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "11111111");
        Dataset<Row> person = sqlContext.read().jdbc("jdbc:mysql://localhost:3306/spark", "person", properties);
//        person.show();

        /**
         * 读取数据库的第二种方法
         */
        Map<String, String> map = new HashMap<>();
        map.put("url", "jdbc:mysql://localhost:3306/spark");
        map.put("driver", "com.mysql.jdbc.Driver");
        map.put("user", "root");
        map.put("password", "11111111");
        map.put("dbtable", "score");

        Dataset<Row> score = sqlContext.read().format("jdbc").options(map).load();
//        score.show();

        /**
         * 读取数据库的第三种方法
         */
        Dataset<Row> score2 = sqlContext.read().format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/spark")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("user", "root")
                .option("password", "11111111")
                .option("dbtable", "score")
                .load();
//        score2.show();

        /**
         * 读取数据库的第四种方法
         */
        Dataset<Row> result = sqlContext.read().jdbc("jdbc:mysql://localhost:3306/spark", "(select person.id, person.age, " +
                "person.name, score.score from person, score where person.id = score.id) T", properties);
        result.show();

        /**
         * 注册表然后联合查询
         */
        person.createOrReplaceTempView("person");
        score.createOrReplaceTempView("score");
        Dataset<Row> sql = sqlContext.sql("select person.id, person.age, person.name, score.score from person, score where person.id = score.id");
        sql.show();

        /**
         * 将结果保存到数据库中
         */
        result.write().mode(SaveMode.Overwrite).jdbc("jdbc:mysql://localhost:3306/spark", "result2", properties);


    }
}
