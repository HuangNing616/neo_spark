# neo_spark说明
这是一个旨在提供Spark学习的项目，可以学习并测试各种函数的相关用法

目前提供以下模块：
* `javacode`
    * `DataSetAndDataFrame`: 提供了DataFrame和DataSet的使用方法
    * `UDFandUDAF`: 提供了UDF和UDAF的使用方法
* `scalacode`
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