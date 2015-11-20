DROP TABLE IF EXISTS `mifi_software`;
CREATE TABLE `mifi_software`(
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `version_serial` varchar(255) NOT NULL DEFAULT '' COMMENT '版本序列号',
  `upgrade_content` varchar(255) NOT NULL DEFAULT '' COMMENT '更新内容',
  `version_type` int(4) NOT NULL DEFAULT '0' COMMENT '版本类型 1:司机版;2:用户版',
  `is_new` int(4) NOT NULL DEFAULT '0' COMMENT '是否为最新版本1：最新版本;0:老版本;',
  `url` int(4) NOT NULL DEFAULT '0' COMMENT '当前版本的下载地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='软件版本信息';
