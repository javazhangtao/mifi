DROP TABLE IF EXISTS `mifi_task_item`;
CREATE TABLE `mifi_task_item`(
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '任务描述',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '任务状态，0表示删除了此任务',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='任务表';
