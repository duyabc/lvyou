DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `fileNumber` varchar(32) DEFAULT NULL,
 `name` varchar(32) DEFAULT NULL,
 `sex` varchar(8) DEFAULT NULL,
 `idCard` varchar(18) DEFAULT NULL,
 `phoneNumber` varchar(11) DEFAULT NULL,
 `regTime` date DEFAULT NULL,
 `password` varchar(32) DEFAULT NULL,
 `email` varchar(32) DEFAULT NULL,
 `birthday` date DEFAULT NULL,
 `remark` varchar(128) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('82', null, '小明', '1', '123456789000999999', '18544279942', '2020-02-08', null, null, null, null);
INSERT INTO `t_member` VALUES ('83', null, '美丽', '1', '132333333333333', '13412345678', '2020-02-11', null, null, null, null);
INSERT INTO `t_member` VALUES ('84', null, '小星星', null, null, '18343569942', '2020-02-13', null, null, null, null);
INSERT INTO `t_member` VALUES ('85', null, '小强', null, null, 18344249942, '2020-02-06', null, null, null, null);
INSERT INTO `t_member` VALUES ('86', null, '红康', null, null, 18544579942, '2020-02-04', null, null, null, null);
INSERT INTO `t_member` VALUES ('87', null, '大海', null, null, 18544274442, '2020-02-06', null, null, null, null);
INSERT INTO `t_member` VALUES ('88', null, '小婷', null, null, 18544269942, '2020-02-10', null, null, null, null);
INSERT INTO `t_member` VALUES ('89', null, '晓晓', null, null, 18544339942, '2020-02-01', null, null, null, null);
INSERT INTO `t_member` VALUES ('90', null, '纯生', null, null, 18544669942, '2020-02-02', null, null, null, null);
INSERT INTO `t_member` VALUES ('91', null, '闰土', null, null, 18544779942, '2020-02-01', null, null, null, null);
INSERT INTO `t_member` VALUES ('92', null, '波波', '2', '234234145432121345', '18019286521', '2020-02-19', null, null, null, null);


DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `member_id` int(11) DEFAULT NULL COMMENT '员会id',
 `orderDate` date DEFAULT NULL COMMENT '约预日期',
 `orderType` varchar(8) DEFAULT NULL COMMENT '约预类型 电话预约/微信预约',
 `orderStatus` varchar(8) DEFAULT NULL COMMENT '预约状态（是否旅游）',
 `setmeal_id` int(11) DEFAULT NULL COMMENT '餐套id',
 PRIMARY KEY (`id`),
 KEY `key_member_id` (`member_id`),
 KEY `key_setmeal_id` (`setmeal_id`),
 CONSTRAINT `key_member_id` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`),
 CONSTRAINT `key_setmeal_id` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, 84, '2020-02-01', '微信预约', '已出游', 3);
INSERT INTO `t_order` VALUES (2, 84, '2020-02-17', '微信预约', '未出游', 3);
INSERT INTO `t_order` VALUES (3, 84, '2020-02-01', '微信预约', '未出游', 3);
INSERT INTO `t_order` VALUES (4, 84, '2020-02-17', '微信预约', '已出游', 4);
INSERT INTO `t_order` VALUES (5, 84, '2020-02-09', '微信预约', '未出游', 4);
INSERT INTO `t_order` VALUES (6, 84, '2020-02-09', '微信预约', '已出游', 4);
INSERT INTO `t_order` VALUES (7, 84, '2020-02-18', '微信预约', '未出游', 5);
INSERT INTO `t_order` VALUES (8, 84, '2020-02-10', '微信预约', '未出游', 6);
INSERT INTO `t_order` VALUES (9, 84, '2020-02-10', '微信预约', '未出游', 6);
INSERT INTO `t_order` VALUES (10, 84, '2020-02-13', '微信预约', '未出游', 7);
INSERT INTO `t_order` VALUES (17, 84, '2020-02-06', '微信预约', '已出游', 12);
