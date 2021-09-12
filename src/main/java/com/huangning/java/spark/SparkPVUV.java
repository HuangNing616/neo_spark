package com.huangning.java.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;

import java.util.*;
import java.util.stream.Stream;

/**
 * 计算pv，uv，以及每个网站每个地区的uv
 */
public class SparkPVUV {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("test");
        conf.setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> rdd1 = sc.textFile("./data/pvuvdata");

        // 计算每个网站每个地区的uv
        rdd1.mapToPair(new PairFunction<String, String, String>() {
            @Override
            public Tuple2<String, String> call(String s) throws Exception {
                return new Tuple2<>(s.split("\t")[5], s.split("\t")[1]);
            }
        }).groupByKey().mapToPair(new PairFunction<Tuple2<String, Iterable<String>>, String, List>() {
            @Override
            public Tuple2<String, List> call(Tuple2<String, Iterable<String>> tp2) throws Exception {
                String webSite =  tp2._1;
                Iterator<String> iter = tp2._2.iterator();
                Map<String, Integer> regionMap = new HashMap<String, Integer>();
                while (iter.hasNext()){
                    String region = iter.next();
                    if (regionMap.containsKey(region)){
                        regionMap.put(region, regionMap.get(region)+1);
                    }else {
                        regionMap.put(region, 1);
                    }
                }

                // 升序比较器
                Comparator<Map.Entry<String, Integer>> valueComparator = Comparator.comparingInt(Map.Entry::getValue);
                // map转换成list进行排序
                List<Map.Entry<String, Integer>> list = new ArrayList<>(regionMap.entrySet());
                // 排序
                list.sort(valueComparator);
                //将顺序倒序
                Collections.reverse(list);
                if(list.size()>3){
                    List<Map.Entry<String, Integer>> returnlist = new ArrayList<>();
                    for (int i = 0 ; i < 3 ; i++){
                        returnlist.add(list.get(i));
                    }
                    return new Tuple2<>(webSite, returnlist);
                }else {
                    return new Tuple2<>(webSite, list);
                }
            }
        }).foreach(new VoidFunction<Tuple2<String, List>>() {
            @Override
            public void call(Tuple2<String, List> tp2) throws Exception {
                System.out.println(tp2);
            }
        });


//        // 计算uv
//        rdd1.map(new Function<String, String>() {
//            @Override
//            public String call(String s) throws Exception {
//                return s.split("\t")[5] + "_" + s.split("\t")[0];
//            }
//        }).distinct().mapToPair(new PairFunction<String, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(String s) throws Exception {
//                return new Tuple2<>(s.split("_")[0], 1);
//            }
//        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1+v2;
//            }
//        }).sortByKey(false).foreach(new VoidFunction<Tuple2<String, Integer>>() {
//            @Override
//            public void call(Tuple2<String, Integer> tp2) throws Exception {
//                System.out.println(tp2);
//            }
//        });


        //计算pv
//        JavaPairRDD<String, Integer> site = rdd1.mapToPair(new PairFunction<String, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(String s) throws Exception {
//                return new Tuple2<>(s.split("\t")[5], 1);
//            }
//        });
//
//        final JavaPairRDD<String, Integer> rdd2 = site.reduceByKey(new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1 + v2;
//            }
//        });
//
//        rdd2.sortByKey(false).foreach(new VoidFunction<Tuple2<String, Integer>>() {
//            @Override
//            public void call(Tuple2<String, Integer> tp2) throws Exception {
//                System.out.println(tp2);
//            }
//        });

    }
}
