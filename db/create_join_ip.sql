DROP TABLE IF EXISTS `mifi_join_ip`;
CREATE TABLE `mifi_join_ip` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `device_ip` varchar(255) DEFAULT NULL COMMENT '设备ip',
  `join_type` int(4) DEFAULT NULL COMMENT '连接类型（0:断开连接；1：加入连接）',
  `user_id` bigint(16) DEFAULT NULL COMMENT 'IP关联的司机',
  `join_user_id` bigint(16) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='设备ip连接信息详情表';
