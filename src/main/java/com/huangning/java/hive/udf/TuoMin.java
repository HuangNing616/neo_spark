package com.huangning.neo.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * Author: 黄宁
 * Date: 2020/02/07
 * Func: 构造Hive的udf函数TuoMin
 * Note: 将字符串的前三位保留，后面用***代替
 */
public class TuoMin extends UDF{
    public Text evaluate(final Text s){
        if (s == null){
            return null;
        }
        String str = s.toString().substring(0, 3) + "***";
        return new Text(str);
    }

    public static void main(String[] args) {
        System.out.println("打印输出");
    }
}
