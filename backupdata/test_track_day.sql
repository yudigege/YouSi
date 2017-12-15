drop table if exists `track_day`;
create table `track_day` (
`id` int(11) NOT NUll auto_increment,
`user_id` int(11) default NUll comment '用户id',
`read_item_number` int(11) default null comment '读后感条目数',
`daily_item_number` int(11) default null comment '日志类条目数',
`read_word_number` int (11) default null comment '读后感字数',
`daily_word_number` int (11) default null comment '日志字数',
`read_record_time_count` bigint collate utf8_bin comment '读后感记录所用总时间',
`daily_record_time_count` bigint collate utf8_bin comment '日志记录总时间',
`creat_day` bigint comment '创建日期年月日',
 `uuid` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'uuid',
`create_time` timestamp NULL default current_timestamp comment '创建时间',
`update_time` timestamp NULL default current_timestamp on update current_timestamp,
primary key (`id`)
)engine=InnoDB default charset=utf8 collate=utf8_bin comment ='每日记录轨迹表每个人的当天数据';











