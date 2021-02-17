# neo_spark说明
该项目从SparkCore, SparkSQL, SparkML以及SparkStreaming这4个方面介绍了Spark, 补充了Scala以及Java的常用语法, 并对LeetCode中经典算法进行了总结

目前提供以下模块：
* `Java模块`
    * `leetcode`
        * `binarytree`: 二叉树经典算法
        * `recursion`: 递归经典算法
        * `stackqueue`: 栈队列经典算法
    * `lesson`
        * `collection`: 容器
        * `thread`: 线程
    * `spark`
        * `dataframe`: DataFrame的创建及使用
        * `udf`: UDF的创建及使用
    * `case`: 测试代码
* `Scala模块`
    * `lesson`
        * `actor`: 通讯模型
        * `base`: 基础语法
        * `collection`: 容器
        * `implicit`: 隐式转换
        * `trait`: 特质
    * `spark`
        * `core`
            * `action`: action算子
            * `transfomation`: transfomation算子
            * `persist`: 持久化算子
        * `ml`
          * `feature`: 特征提取
          * `pipeline`: 管道流
        * `sql`
            * `dataframe`: DataFrame的创建及使用
            * `udf`: UDF的创建及使用
            * `window`: 开窗函数的创建及使用
        * `streaming`: 
            读取，保存以及窗口相关操作
  * `case`: 测试代码
    
      
      * ``
    * `lesson`: 介绍了容器，线程等常用语法
    * `spark`: 针对SparkSQL中的UDF, DataFrame语法进行了描述
    * `core`
        * `actions`: 提供了actions算子的使用方法
        * `trainfromations`: 提供了transformations算子的使用方法
        * `persist`: 提供了persist算子的使用方法
        * `examples`: 提供了累加器，广播变量以及WordCount等实现方法
    * `sql`
        * `DataSetAndDataFrame`: 提供了DataFrame和DataSet的使用方法
        * `UDFandUDAF`: 提供了UDF和UDAF的使用方法
        * `windows`: 提供了基于Hive和MySQL的开窗函数方法
    * `streaming`: 提供了spark streaming中一些算子的使用方法


# 本地导入
如果要给该项目贡献代码，可以先将代码克隆到本地

```bash
$ git clone git@github.com:HuangNing616/SparkNote.git
```