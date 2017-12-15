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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '书名',
  `author` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  `key_words` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '关键字',
  `content` text COLLATE utf8_bin COMMENT '内容',
  `life_course` text COLLATE utf8_bin COMMENT '人生经历',
  `nationality` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '国籍',
  `birth_time` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '出生时间',
  `death_time` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '去世时间',
  `dynasty` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '朝代',
  `uuid` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'uuid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookName_UNIQUE` (`book_name`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='书籍表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (36,'西游记','吴承恩','das','《三国演义》描写了从东汉末年到西晋初年之间近百年的历史风云，以描写战争为主，诉说了东汉末年的群雄割据混战和汉、魏、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事。反映了三国时代各类社会斗争与矛盾的转化，并概括了这一时代的历史巨变，塑造了一群叱咤风云的三国英雄人物。','生平','中国','null','null','唐朝','null','2017-09-28 09:43:58','2017-09-28 09:43:58'),(37,'红楼梦','曹雪芹','das','《红楼梦》是一部具有世界影响力的人情小说作品[3]  ，举世公认的中国古典小说巅峰之作，中国封建社会的百科全书，传统文化的集大成者。小说以贾、史、王、薛四大家族的兴衰为背景，以贾府的家庭琐事、闺阁闲情为脉络，以贾宝玉、林黛玉、薛宝钗的爱情婚姻故事为主线，刻画了以贾宝玉和金陵十二钗为中心的正邪两赋有情人的人性美和悲剧美。通过家族悲剧、女儿悲剧及主人公的人生悲剧，揭示出封建末世危机','生平','中国','null','null','唐朝','null','2017-09-28 10:01:07','2017-09-28 10:01:07'),(38,'三国演义','罗贯中','null','《三国演义》描写了从东汉末年到西晋初年之间近百年的历史风云，以描写战争为主，诉说了东汉末年的群雄割据混战和汉、魏、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事。反映了三国时代各类社会斗争与矛盾的转化，并概括了这一时代的历史巨变，塑造了一群叱咤风云的三国英雄人物','null','中国','birth_time','null','唐朝','null','2017-10-18 09:26:00','2017-10-18 09:26:00'),(39,'水浒传','施耐庵',NULL,'《水浒传》是一部以描写古代农民起义为题材的长篇小说。它形象地描绘了农民起义从发生、发展直至失败的全过程，深刻揭示了起义的社会根源，满腔热情地歌颂了起义英雄的反抗斗争和他们的社会理想，也具体揭示了起义失败的内在历史原因。',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-19 11:38:48','2017-10-19 11:38:48'),(40,'dggd','多个',NULL,'咕咕咕咕多所多过所多过付所多过',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-26 08:02:52','2017-10-26 08:02:52'),(41,'十大歌手电饭锅','更多的',NULL,'根深蒂固第三方个地方',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-26 08:02:52','2017-10-26 08:02:52'),(42,'关大叔','感受到',NULL,'三个地方故事讽德诵功分多个',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-26 08:02:52','2017-10-26 08:02:52'),(43,'单方事故','感受到',NULL,'电饭锅是非得失感受到',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-26 08:02:52','2017-10-26 08:02:52'),(44,'十多个','个',NULL,'工时费根深蒂固的',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-26 08:02:52','2017-10-26 08:02:52'),(45,'食发鬼','三个',NULL,'丝瓜豆腐羹',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-26 08:02:52','2017-10-26 08:02:52'),(46,'山东饭馆','三个',NULL,'告诉对方的根深蒂固fds',NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-26 08:02:52','2017-12-05 10:08:47');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
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
