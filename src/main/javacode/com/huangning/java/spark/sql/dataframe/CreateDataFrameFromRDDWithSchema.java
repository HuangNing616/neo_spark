package com.huangning.java.spark.sql.dataframe;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

public class CreateDataFrameFromRDDWithSchema {
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

        /**
         * 转成Row类型的RDD
         */
        final JavaRDD<Row> rowRDD = lineRDD.map(new Function<String, Row>() {
            private static final long serialVersionUID = 1;

            @Override
            public Row call(String s) throws Exception {
                return RowFactory.create(
                        s.split(",")[0],
                        s.split(",")[1],
                        Integer.valueOf(s.split(",")[2])
                );
            }
        });
        /**
         * 动态构建DataFrame中的元数据，字段可以来自字符串，也可以来自外部数据库
         */
        final List<StructField> asList = Arrays.asList(
                DataTypes.createStructField("id", DataTypes.StringType, true),
                DataTypes.createStructField("name", DataTypes.StringType, true),
                DataTypes.createStructField("age", DataTypes.IntegerType, true)
        );

        StructType schema = DataTypes.createStructType(asList);

        Dataset<Row> dataFrame = sqlContext.createDataFrame(rowRDD, schema);
        dataFrame.show();
    }
}
