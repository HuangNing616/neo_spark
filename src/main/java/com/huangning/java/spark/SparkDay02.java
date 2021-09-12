package com.huangning.java.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.sources.In;
import scala.Tuple2;

import java.util.*;

public class SparkDay02 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("test");
        conf.setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("Error");

        JavaRDD<String> rdd1 = sc.parallelize(Arrays.asList("a", "b", "c", "d", "d"),2);

        rdd1.foreachPartition(new VoidFunction<Iterator<String>>() {
            @Override
            public void call(Iterator<String> iter) throws Exception {
                List<String> li = new ArrayList<String>();
                System.out.println("启动数据库");
                while (iter.hasNext()) {
                    String s = iter.next();
                    li.add(s);
                    System.out.println("current info is " + s);

                }
                System.out.println("关闭数据库");
            }
        });

//        /**
//         * mapPartitions
//         */
//        final long count = rdd1.mapPartitions(new FlatMapFunction<Iterator<String>, String>() {
//            @Override
//            public Iterator<String> call(Iterator<String> iter) throws Exception {
//                List<String> li = new ArrayList<String>();
//                System.out.println("启动数据库");
//                while (iter.hasNext()) {
//                    String s = iter.next();
//                    li.add(s);
//                    System.out.println("current info is " + s);
//
//                }
//                System.out.println("关闭数据库");
//                return li.iterator();
//            }
//        }).count();
//        System.out.println("mapPartitions 之后的RDD中元素个数为" + count);


//        JavaRDD<String> rdd2 = sc.parallelize(Arrays.asList("a", "b", "c", "f"));
//        JavaRDD<Integer> rdd2 = sc.parallelize(Arrays.asList(100, 200, 300, 400));

        /**
         * zipWithIndex
         */
//        rdd1.zipWithIndex().foreach(new VoidFunction<Tuple2<String, Long>>() {
//            @Override
//            public void call(Tuple2<String, Long> tp2) throws Exception {
//                System.out.println(tp2);
//            }
//        });

        /**
         * zip
         */
//        rdd1.zip(rdd2).foreach(new VoidFunction<Tuple2<String, Integer>>() {
//            @Override
//            public void call(Tuple2<String, Integer> tp2) throws Exception {
//                System.out.println(tp2);
//            }
//        });



        /**
         * intersect
         */
//        rdd1.intersection(rdd2).foreach(new VoidFunction<String>() {
//            @Override
//            public void call(String s) throws Exception {
//                System.out.println(s);
//            }
//        });

        /**
         * subtract
         */
//        rdd1.subtract(rdd2).foreach(new VoidFunction<String>() {
//            @Override
//            public void call(String s) throws Exception {
//                System.out.println(s);
//            }
//        });


        /**
         * distinct 去重
         */
//        final JavaRDD<String> distinceRDD = rdd1.distinct();
//        distinceRDD.foreach(new VoidFunction<String>() {
//            @Override
//            public void call(String s) throws Exception {
//                System.out.println(s);
//            }
//        });


        // 可以看出并没有生成kv格式的rdd，即PairRDD
//        final JavaRDD<Tuple2> parallelize = sc.parallelize(Arrays.asList(
//                new Tuple2("zhangsan", 18),
//                new Tuple2("lisi", 32),
//                new Tuple2("wangwu", 32)));

//        JavaPairRDD<String, Integer> nameRDD = sc.parallelizePairs(Arrays.asList(
//                new Tuple2<String, Integer>("zhangsan", 18),
//                new Tuple2<String, Integer>("lisi", 32),
//                new Tuple2<String, Integer>("maliu", 190),
//                new Tuple2<String, Integer>("wangwu", 32)));
//
//        JavaPairRDD<String, Integer> scoreRDD = sc.parallelizePairs(Arrays.asList(
//                new Tuple2<String, Integer>("zhangsan", 100),
//                new Tuple2<String, Integer>("lisi", 200),
//                new Tuple2<String, Integer>("wangwu", 300)));




        /**
         * union
         */
//        JavaPairRDD<String, Integer> unionResult = nameRDD.union(scoreRDD);
//        unionResult.foreach(new VoidFunction<Tuple2<String, Integer>>() {
//            @Override
//            public void call(Tuple2<String, Integer> tp2) throws Exception {
//                System.out.println(tp2);
//            }
//        });


        /**
         * fullOuterJoin
         */
//        JavaPairRDD<String, Tuple2<Optional<Integer>, Optional<Integer>>> resultFull = nameRDD.fullOuterJoin(scoreRDD);



        /**
         * rightOuterJoin
         */
//        JavaPairRDD<String, Tuple2<Optional<Integer>, Integer>> resultRight = nameRDD.rightOuterJoin(scoreRDD);


        /**
         *  leftOuterJoin
         */
//        JavaPairRDD<String, Tuple2<Integer, Optional<Integer>>> resultLeft = nameRDD.leftOuterJoin(scoreRDD);
//        resultLeft.foreach(new VoidFunction<Tuple2<String, Tuple2<Integer, Optional<Integer>>>>() {
//            @Override
//            public void call(Tuple2<String, Tuple2<Integer, Optional<Integer>>> tp2) throws Exception {
//                String key = tp2._1;
//                Integer value1 = tp2._2._1;
//                Optional value2 = tp2._2._2;
//
//                System.out.println("key = "+ key + "value1 = " + value1 + " value2 = " + value2.orElse("没有值"));
//            }
//        });


        /**
         * join
         */
//        final JavaPairRDD<String, Tuple2<Integer, Integer>> result = nameRDD.join(scoreRDD);
//        result.foreach(new VoidFunction<Tuple2<String, Tuple2<Integer, Integer>>>() {
//            @Override
//            public void call(Tuple2<String, Tuple2<Integer, Integer>> tp2) throws Exception {
//                System.out.println(tp2);
//            }
//        });


    }

}
