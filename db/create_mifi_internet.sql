DROP TABLE IF EXISTS `mifi_internet`;
CREATE TABLE `mifi_internet`(
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) NOT NULL DEFAULT '' COMMENT '设备IP',
  `userId` bigint(16) NOT NULL DEFAULT '0' COMMENT '司机ID',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '0:司机断开网络，结束赚钱;1:打开网络，开始赚钱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='IP版mifi的上网许可';
