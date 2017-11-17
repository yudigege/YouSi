search_record	CREATE TABLE `search_record` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `key_words` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '搜索关键字',
   `frequency` INT DEFAULT 0 COMMENT '搜索次数',
   `uuid` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'uuid',
   `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='搜索关键词频率表'