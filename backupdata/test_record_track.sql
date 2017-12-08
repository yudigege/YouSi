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
-- Table structure for table `record_track`
--

DROP TABLE IF EXISTS `record_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record_track` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `read_item_number` int(11) DEFAULT NULL COMMENT '读后感条目数',
  `daily_item_number` int(11) DEFAULT NULL COMMENT '日志类条目数',
  `read_word_number` int(11) DEFAULT NULL COMMENT '读后感字数',
  `daily_word_number` int(11) DEFAULT NULL COMMENT '日志字数',
  `read_record_time_count` bigint COLLATE utf8_bin COMMENT '读后感记录所用总时间',
  `daily_record_time_count` bigint COLLATE utf8_bin COMMENT '日志记录所用总时间',
  `continue_days` int(11) NOT NULL DEFAULT '0' COMMENT '连续记录天数',
  `uuid` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'uuid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `continue_date_time` bigint  COMMENT '继续添加时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='记录轨迹追踪表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record_track`
--

LOCK TABLES `record_track` WRITE;
/*!40000 ALTER TABLE `record_track` DISABLE KEYS */;
/*!40000 ALTER TABLE `record_track` ENABLE KEYS */;
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
