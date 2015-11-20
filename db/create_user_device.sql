DROP TABLE IF EXISTS `mifi_user_device`;
CREATE TABLE `mifi_user_device` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(16) NOT NULL COMMENT '用户ID',
  `device_id` bigint(16) NOT NULL COMMENT '设备Id',
  `internet_status` int(4) NOT NULL DEFAULT '0' COMMENT '联网状态(0:停用，1：正常)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(4) DEFAULT NULL DEFAULT '1' COMMENT '状态(0:停用，1：正常)',
  `rank` int(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户设备关联表'