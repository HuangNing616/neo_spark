package com.huangning.java.spark.sql.dataframe;

import java.io.Serializable;

public class Person implements Serializable {
    private String ID;
    private String name;
    private Integer age;


    public void setID(String IDD){
        this.ID = IDD;
    }

    public String getHuangIDD() {
        return ID;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getHuangName() {
        return name;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getHuangAge() {
        return age;
    }

    // 重写序列化接口中的方法
    @Override
    public String toString() {
        return "Person [id==" + ID + ", name=" + name + ", age=" + age + "]";
    }
}
