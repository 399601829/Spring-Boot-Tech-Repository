# Spring-Boot-Tech-Repository
## 1、spring-boot-restful

Springboot 实现 Restful 服务，基于 HTTP / JSON 传输，适用于前后端分离。

## 2、spring-boot-elasticsearch

Spring Boot 整合 Elasticsearch，实现 function score query 权重分查询，
Elasticsearch 默认配置 IK 及使用 Java AnalyzeRequestBuilder 获取分词结果。

ES 的使用场景大致分为两块
1. 全文检索。加上分词（IK 是其中一个）、拼音插件等可以成为强大的全文搜索引擎。
2. 日志统计分析。可以实时动态分析海量日志数据。

版本对应：
Spring Data Elasticsearch Spring Boot version matrix

Spring Boot Version (x) ；Spring Data Elasticsearch Version (y) ；Elasticsearch Version (z)

x <= 1.3.5 ；y <= 1.3.4 ；z <= 1.7.2*

x >= 1.4.x ；2.0.0 <=y < 5.0.0** ；2.0.0 <= z < 5.0.0**

在 Elasticsearch-analysis-ik  官网 https://github.com/medcl/elasticsearch-analysis-ik 中可以看到，其中IK版、ES版本需要对应；默认配置了 IK 分词器，则 DSL 去 ES 查询时会自动调用 IK 分词。
如果想要自定义词库，比如比较偏的领域性。可以参考 Elasticsearch-analysis-ik GiHub 地址去具体查阅。

3、基本环境配置

在 MySQL 中，创建数据库 springbootdb：
````
CREATE DATABASE springbootdb;
````
创建表 city
````
DROP TABLE IF EXISTS  `city`;
CREATE TABLE `city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
  `province_id` int(10) unsigned  NOT NULL COMMENT '省份编号',
  `city_name` varchar(25) DEFAULT NULL COMMENT '城市名称',
  `description` varchar(25) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
````


