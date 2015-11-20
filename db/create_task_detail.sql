DROP TABLE IF EXISTS `mifi_task_detail`;
CREATE TABLE `mifi_task_detail`(
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '任务名字描述',
  `userId` bigint(16) NOT NULL DEFAULT '0' COMMENT '完成任务的用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户完成的历史任务';
