

### 设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。并在新结构在演示常见的增删改查操作。代码、sql和配置文件，上传到github
使用了ShardingSphere-Proxy, 增删改查操作在distributed_db的CrudExample中，配置在resource文件夹

### 基于hmily TCC或ShardingSphere的Atomikos XA实现一个简单的分布式事务应用demo（二选一），提交到Github
将Hmily的Springcloud示例跑通了，自己的例子还未完成