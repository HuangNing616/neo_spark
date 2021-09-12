package com.huangning.java.spark.sql.dataframe;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class CreateDataFrameFromRDDWithReflection {
    public static void main(String[] args) {
        /**
         * 1.自定义的类级别必须是public
         * 2.RDD转换成Daraframe会把自定义类中字段的名称按照Assci码排序
         * 3.自定义类中要实现序列化接口, 并且一定要重写get方法！！！
         */
        SparkConf conf = new SparkConf();
        conf.setAppName("RDDWithReflection").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        JavaRDD<String> lineRDD = sc.textFile("./data/person.txt");

        final JavaRDD<Person> personRDD = lineRDD.map(new Function<String, Person>() {
            /**
             *
             */
            private static final long serialVersionUID = 1;

            @Override
            public Person call(String line) throws Exception {
                Person p = new Person();
                p.setID(line.split(",")[0]);
                p.setName(line.split(",")[1]);
                p.setAge(Integer.valueOf(line.split(",")[2]));
                return p;
            }
        });


        /**
         * 传入进去Person.class的时候，sqlContext是通过反射的方式创建DataFrame
         * 在底层通过反射的方式获得Person的所有field,结合RDD本身，就有了DataFrame
         */
        Dataset<Row> df = sqlContext.createDataFrame(personRDD, Person.class);
        df.show();
        df.printSchema();
        df.registerTempTable("person");

        Dataset<Row> sql = sqlContext.sql("select  huangName, huangIDD, huangAge from person where huangIDD = 2");
        sql.show();
    }
}
