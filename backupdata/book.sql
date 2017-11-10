book	CREATE TABLE `book` (
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
   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`),
   UNIQUE KEY `bookName_UNIQUE` (`book_name`)
 ) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='书籍表'