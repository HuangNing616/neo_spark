package com.huangning.java.spark.sql.udf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.expressions.MutableAggregationBuffer;
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UDAF {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local").setAppName("UDAF");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext spark = new SQLContext(sc);

        // 通过动态Schema的方式将RDD转换成DataFrame
        JavaRDD<String> rdd = sc.parallelize(Arrays.asList("zhangsan", "lisi", "wangwu", "lisi", "wangwu"));
        JavaRDD<Row> rowRDD = rdd.map(new Function<String, Row>() {
            @Override
            public Row call(String s) throws Exception {
                // 将字符串分装成Row类型--用RowFactory
                return RowFactory.create(s);
            }
        });

        List<StructField> fields = new ArrayList<StructField>();
        fields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        StructType schema = DataTypes.createStructType(fields);
        Dataset<Row> df = spark.createDataFrame(rowRDD, schema);
        df.createOrReplaceTempView("students");

        /**
         * 注册一个UDAF函数，实现统计相同值的个数
         * 注意：这里可以自定义一个类继承UserDefinedAggregateFunction类也是可以的
         */
        spark.udf().register("NAMECOUNT", new UserDefinedAggregateFunction() {
            /**
             * 在RDD的每个分区的每个分组更新结果
             * @param buffer
             * @param input
             */
            @Override
            public void update(MutableAggregationBuffer buffer, Row input) {
                buffer.update(0, buffer.getInt(0) + 1);
            }

            /**
             * 合并update操作，可能是一个节点上发生的，也可能是多个节点上发生的
             * @param buffer1：大聚合的时候上一次聚合的结果
             * @param buffer2：新传入的update结果
             */
            @Override
            public void merge(MutableAggregationBuffer buffer1, Row buffer2) {
                buffer1.update(0, buffer1.getInt(0) + buffer2.getInt(0));
            }


            /**
             * 每个分区的每组初始化结果
             * @param buffer
             */
            @Override
            public void initialize(MutableAggregationBuffer buffer) {
                buffer.update(0,0);
            }

            /**
             * 指定输入字段的字段以及类型
             * @return
             */
            @Override
            public StructType inputSchema() {
                return DataTypes.createStructType(
                        Arrays.asList(
                                DataTypes.createStructField("name111", DataTypes.StringType, true)
                        )
                );
            }

            /**
             * 在进行聚合操作的时候，所需要处理数据的结果的类型
             */
            @Override
            public StructType bufferSchema() {
                return DataTypes.createStructType(Arrays.asList(DataTypes.createStructField("1111", DataTypes.IntegerType, true)));
            }

            /**
             * 最后翻一个和dataType方法类型要一直的类型，返回UDAF最后的计算结果
             * @param buffer
             * @return:
             */
            @Override
            public Object evaluate(Row buffer) {
                return buffer.getInt(0);
            }

            /**
             * 指定UDAF函数计算后返回的结果类型
             */
            @Override
            public DataType dataType() {
                return DataTypes.IntegerType;
            }


            /**
             * 确保一致性一般用true，用来标记针对给定的一组输入，UDAF是否总是生成相同的结果
             * @return
             */
            @Override
            public boolean deterministic() {
                return true;
            }
        });

        spark.sql("select name, NAMECOUNT(name) from students group by name").show(100);

        sc.stop();
    }
}
