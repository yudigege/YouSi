-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: 116.196.109.167    Database: test
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` int(11) DEFAULT '0' COMMENT '0：未知；1：男；2：女',
  `mobile` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `real_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `job` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '职位',
  `company` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '公司',
  `bio` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '个人简介',
  `blog` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '博客',
  `github` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'github',
  `uuid` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'uuid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `uuid_UNIQUE` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (50,1,'12345678','邮编','真是名字','诗雅','http://192.168.1.133:8080/users/avatar/50/1512033734607.jpg','it','E:\\backsoftware\\eclipse-jee-oxygen-R-win32-x86_64\\eclipse\\50\\1512033734607.jpg','人咯啦咯啦咯啦咯啦KKK咯哦哦KKK咯哦哦啦咯就来咯啦啦咯啦咯啦咯啦啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯啦咯','www.baidu.com','wqeqwe',NULL,'2017-09-28 10:07:53','2017-09-28 10:07:53'),(119,0,'45668','null','null','null','null','null','null','null','null','null',NULL,'2017-11-03 07:27:57','2017-11-03 07:27:57'),(120,0,'15530884416','null','null','null','null','null','null','null','null','null',NULL,'2017-11-07 06:54:55','2017-11-07 06:54:55'),(121,0,'963852','null','null','null','null','null','null','null','null','null',NULL,'2017-11-09 07:20:17','2017-11-09 07:20:17'),(122,0,' 12345678','null','null','null','null','null','null','null','null','null',NULL,'2017-11-30 01:15:13','2017-11-30 01:15:13'),(123,0,'123456','null','null','超市','http://192.168.1.133:8080/users/avatar/123/1512029941059.jpg','null','/usr/java/tomcat-9/bin/123/1512029941059.jpg','村上春树','www','null',NULL,'2017-11-30 08:17:12','2017-11-30 08:17:12'),(124,0,'chunfeng','null','null','雅诗','null','null','null','null','null','null',NULL,'2017-11-30 08:30:15','2017-11-30 08:30:15'),(125,0,'youyuan','null','null','bbbjjjbj','null','null','null','vbbbbn','null','df',NULL,'2017-11-30 08:37:13','2017-12-05 10:14:17'),(126,1,'98760','null','null','诗雅颂','null','null','null','Klondike','嗯呢','null',NULL,'2017-11-30 09:13:40','2017-11-30 09:13:40');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-06 18:09:59
