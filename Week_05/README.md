学习笔记

#### 写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提交到Github。

具体代码在learn_spring模块下的BeanHandler类里面, 下面分别代表了使用xml, 注解，手工beanDefinition， beanDefinitionBuilder, BeanDefinitionReaderUtils, BeanDefinitionRegistryPostProcessor 等几种方式
```java
//        xml();
//        annotation();
//        manuallyBeanDefinition();
//        manuallyBeanDefinitionBuilder();
//        manuallyBeanDefinitionReaderUtils();
//        manuallyBeanDefinitionRegistryPostProcessor();
```

#### 给前面课程提供的Student/Klass/School实现自动配置和Starter。

my_spring_starter是实现的starter模块，关键类是SchoolAutoConfiguration和SchoolProperties

my_springboot_demo是引用starter的模块，启动SpringBootDemoApplication并将`shcool.enabled`配置为true就可以从context获取到配置好的school bean

#### 研究一下JDBC接口和数据库连接池，掌握它们的设计和用法

具体代码在learn_jdbc模块下，create_table.sql是建表语句，BasicJdbc是使用原生JDBC的增删改查，TxAndBatchJdbc是使用事务和批处理，HikariDemo是使用Hikari连接池