package com.huangning.java.spark;

import javafx.util.Pair;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;


/**
 * 一定要将配置好的conf塞入新创建的上下文对象中
 */
public class SparkWordCount {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf();
        conf.setAppName("wc");
        conf.setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("./data/words");

        // java 包里面支持lambda表达式
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" ")).iterator();
            }
        });

        JavaPairRDD<String, Integer> pairwords = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        JavaPairRDD<String, Integer> result = pairwords.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        JavaPairRDD<String, Integer> finalresult = result.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<String, Integer> tp2) throws Exception {
                return tp2.swap();
            }
        }).sortByKey(false).mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<Integer, String> tp2) throws Exception {
                return tp2.swap();
            }
        });

        // 将RDD进行降序排序然后输出
        finalresult.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> tp) throws Exception {
                System.out.println(tp);
            }
        });

        sc.stop();
    }
}
