package com.huangning.java.spark;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 1. java 里面没有sortBy算子，只有sortByKey
 * 2. JavaPairRDD<String, String> 叫k,v格式的rdd， 而JavaRDD<Tuple2<String, String>>只叫做tuple类型的rdd
 * 3. transformation 算子中的sample中的fraction只代表大约的数量，即百分之10左右的数据
 *    但是如果输入大于1的数，比如5：那么就会抽出当前样本量5倍的数据，但是scala中只代表抽出5条数据
 */
public class Day01java {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("test");
        conf.setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("./data/words");
        sc.setLogLevel("Error");

        JavaRDD<String> sample = lines.sample(true, 0.1, 100L);
        sample.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });

//        // return the first five item of the data
//        List<String> take = lines.take(4);
//        System.out.println(take);

//        // return the first item of the data
//        String first = lines.first();
//        System.out.println(first);

//        // count 算子
//        long count = lines.count();
//        System.out.println(count);

//        // collect 算子
//        List<String> collect = lines.collect();
//        for(String one: collect){
//            System.out.println(one);
//        }



//        // 按照提示来传递function, s.split(" ")是一个字符串数组，Arrays.asList是将String数组转成了一个list,
//        // 然后可以通过List的iterator方法将其转换成Iterator
//        JavaPairRDD<String, Integer> reduceRDD = lines.flatMap(new FlatMapFunction<String, String>() {
//            @Override
//            public Iterator<String> call(String s) throws Exception {
//                List<String> list = Arrays.asList(s.split(" "));
//                System.out.println("****" + list.iterator() + "****");
//                return list.iterator();
//            }
//        }).mapToPair(new PairFunction<String, String, Integer>() {
//
//            @Override
//            public Tuple2<String, Integer> call(String s) throws Exception {
//                return new Tuple2<>(s, 1);
//            }
//        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1 + v2;
//            }
//        });
//
//        // 想要转出一对一格式的数据，并且转出来的还得是tuple类型
//        reduceRDD.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
//            @Override
//            public Tuple2<Integer, String> call(Tuple2<String, Integer> t2) throws Exception {
//                return t2.swap();
//            }
//        }).sortByKey(false).mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(Tuple2<Integer, String> t3) throws Exception {
//                return t3.swap();
//            }
//        }).foreach(new VoidFunction<Tuple2<String, Integer>>() {
//            @Override
//            public void call(Tuple2<String, Integer> t4) throws Exception {
//                System.out.println(t4);
//            }
//        });

//        // 转成k，v格式的时候用mapToPair，不转成k，v格式的时候用map
//        JavaPairRDD<String, String> stringStringJavaPairRDD = lines.mapToPair(new PairFunction<String, String, String>() {
//            @Override
//            public Tuple2<String, String> call(String s) throws Exception {
//                return new Tuple2<String, String>(s, s + "#");
//            }
//        });
//
//        JavaRDD<Tuple2<String, String>> map = lines.map(new Function<String, Tuple2<String, String>>() {
//            @Override
//            public Tuple2<String, String> call(String line) throws Exception {
//                return new Tuple2<>(line, line + "*");
//            }
//        });

//        map.foreach(new VoidFunction<String>() {
//            @Override
//            public void call(String s) throws Exception {
//                System.out.println(s);
//            }
//        });

//        // String 表示进来的当前行数据，boolean表示返回的值类型
//        JavaRDD<String> result = lines.filter(new Function<String, Boolean>() {
//            @Override
//            public Boolean call(String s) throws Exception {
//
//                return "hello spark".equals(s);
//            }
//        });
//
//        long count = result.count();
//        System.out.println(count);

//        result.foreach(new VoidFunction<String>() {
//            @Override
//            public void call(String s) throws Exception {
//                System.out.println(s);
//            }
//        });

//        stop and close have the same meaning
//        sc.close();
        sc.stop();


    }
}
