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
-- Table structure for table `days_report`
--

DROP TABLE IF EXISTS `days_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `days_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` int(11) NOT NULL COMMENT '0：日记；1:读后感',
  `key_words` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '关键字',
  `content` text COLLATE utf8_bin COMMENT '内容',
  `book_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '书名',
  `bg_color` varchar(45) COLLATE utf8_bin DEFAULT '#00000000' COMMENT '文本背景颜色',
  `bg_imgUrl` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '文本背景图片',
  `txt_size` int(11) DEFAULT '13' COMMENT '文本大小',
  `title` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `uuid` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'uuid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `record_consume_start_time` bigint(20) DEFAULT NULL,
  `record_consume_end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日常记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `days_report`
--

LOCK TABLES `days_report` WRITE;
/*!40000 ALTER TABLE `days_report` DISABLE KEYS */;
INSERT INTO `days_report` VALUES (16,11,1,'23','fds','大学','#00000000',NULL,13,'sfd',NULL,'2017-10-24 07:36:49','2017-10-24 07:36:49',NULL,NULL),(17,11,0,'fds','古之欲明明德于天下者，先治其国。欲治其国者，先齐其家10。欲齐其家者，先修其身11。欲修其身者，先正其心。欲正其心者，先诚其意。欲诚其意者，先致其知12。致知在格物13。物格而后知至，知至而后意诚，意诚而后心正，心正而后身修，身修而后家齐，家齐而后国治，国治而后天下平。','大学','dfs',NULL,13,NULL,NULL,'2017-10-24 07:36:49','2017-10-24 07:36:49',NULL,NULL),(18,11,1,'ihhl','所谓诚其意者20，毋自欺也21。如恶恶臭22，如好好色23，此之谓自谦24。故君子必慎其独也25。小人闲居为不善26，无所不至，见君子而后厌然27，掩其不善而著其善28。 人之视己，如见其肺肝然，则何益矣29。此谓诚于中30，形于外31，故君子必慎其独也。 曾子曰：“十目所视，十手所指，其严乎32！”富润屋33，德润身34，心广体胖35，故君子必诚其意。','大学','#00000000',NULL,13,NULL,NULL,'2017-10-24 07:38:30','2017-10-24 07:38:30',NULL,NULL),(19,11,0,'null','tdtg','null','null','null',0,'hfgfgh','null','2017-10-31 02:47:42','2017-10-31 02:47:42',NULL,NULL),(20,11,0,'null','tdtg','null','null','null',0,'hfgfgh','null','2017-10-31 02:47:49','2017-10-31 02:47:49',NULL,NULL),(21,0,0,'null','qingjiaoyue ','null','null','null',0,'tizi','null','2017-10-31 04:00:26','2017-10-31 04:00:26',NULL,NULL),(22,0,0,'null','asdf','null','null','null',0,'duhogan','null','2017-10-31 06:51:36','2017-10-31 06:51:36',NULL,NULL),(23,0,0,'null','sadf','null','null','null',0,'duhougan','null','2017-10-31 06:53:09','2017-10-31 06:53:09',NULL,NULL),(24,0,0,'null','旅途','null','null','null',0,'吐了','null','2017-10-31 09:41:17','2017-10-31 09:41:17',NULL,NULL),(25,0,0,'null','sfddsa d','null','null','null',0,'shiyiyue','null','2017-11-02 02:30:07','2017-11-02 02:30:07',NULL,NULL),(31,120,0,'null','隔壁的老王','null','null','null',0,'王煜阿','null','2017-11-07 06:56:59','2017-11-07 06:56:59',NULL,NULL),(37,11,0,'null','内容','null','null','null',13,'标题','null','2017-11-17 09:00:03','2017-11-17 09:00:03',NULL,NULL),(38,123,0,'null','超市','null','null','null',0,'超市','null','2017-11-30 08:17:36','2017-11-30 08:17:36',NULL,NULL),(39,124,0,'null','屠呦呦','null','null','null',0,'这时候','null','2017-11-30 08:31:22','2017-11-30 08:31:22',NULL,NULL),(40,125,0,'null','hhhh','null','null','null',0,'bbb','null','2017-11-30 08:37:32','2017-11-30 08:37:32',NULL,NULL),(41,125,1,'null','hjj','vvvhhhh','null','null',0,'null','null','2017-11-30 08:38:32','2017-11-30 08:38:32',NULL,NULL),(43,126,0,'null','看记录','null','null','null',0,'科技','null','2017-11-30 09:34:54','2017-11-30 09:34:54',NULL,NULL),(47,50,0,'null','就来了','null','null','null',0,'啦啦啦啦','null','2017-11-30 09:53:12','2017-11-30 09:53:12',NULL,NULL),(48,50,0,'null','啦咯啦咯啦咯啦咯','null','null','null',0,'f来了ds','null','2017-12-05 09:13:14','2017-12-05 10:05:53',1512463464383,1512465195072);
/*!40000 ALTER TABLE `days_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-06 18:09:58
