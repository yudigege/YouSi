days_report	CREATE TABLE `days_report` (
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
   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日常记录表'