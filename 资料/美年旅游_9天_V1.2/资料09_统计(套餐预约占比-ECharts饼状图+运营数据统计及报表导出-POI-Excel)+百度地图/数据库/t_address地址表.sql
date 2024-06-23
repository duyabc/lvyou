DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lng` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (1, '深圳市宝安区尚硅谷(深圳校区)', '113.844656', '22.632231');
INSERT INTO `t_address` VALUES (2, '深圳市世界之窗', '113.97939921156106', '22.540746033109636');
INSERT INTO `t_address` VALUES (3, '深圳市宝安区深圳宝安国际机场-T3航站楼', '113.819598', '22.631062');
INSERT INTO `t_address` VALUES (6, '深圳市北站', '114.03552911268908', '22.615107645742687');
INSERT INTO `t_address` VALUES (8, '深圳市光明区政府', '113.94256006193123', '22.754465883635367');
INSERT INTO `t_address` VALUES (9, '深圳市西部硅谷大厦', '113.8444607884076', '22.6320909124466');
INSERT INTO `t_address` VALUES (10, '深圳市莲花山公园', '114.06519268773812', '22.559256676825059');
INSERT INTO `t_address` VALUES (18, '深圳市宝安区羊台山森林公园', '113.97127', '22.657618');
INSERT INTO `t_address` VALUES (24, '深圳市宝安区宝安公园', '113.909263', '22.592267');
INSERT INTO `t_address` VALUES (26, '深圳市盐田区大梅沙海滨公园', '114.314011', '22.598196');
