DROP TABLE IF EXISTS `mifi_user_token`;
CREATE TABLE `mifi_user_token` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL COMMENT 'token,UUID',
  `user_id` bigint(16) NOT NULL COMMENT '关联的用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='token表';
