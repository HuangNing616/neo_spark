package com.huangning.neo.hive.jdbc;

import java.sql.*;

public class HiveJdbcClient {

    public static void main(String[] args) throws SQLException {

    // 1. 声明驱动类
    try{
        String driverName = "org.apache.hive.jdbc.HiveDriver";
        Class.forName(driverName);
    }catch (ClassNotFoundException e){
        e.printStackTrace();
    }

    // 2. 创建连接
    Connection conn = DriverManager.getConnection("jdbc:hive2://node2:10000/default", "root", "");
    Statement stmt = conn.createStatement();

    // 3. 执行SQL查询
    String sql = "select * from psn3 limit 5";
    ResultSet res = stmt.executeQuery(sql);
    while (res.next()){
        System.out.println(res.getString(1) + "-" + res.getString("name"));
    }
}
}
