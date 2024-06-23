DROP TABLE IF EXISTS `t_setmeal`;
CREATE TABLE `t_setmeal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1000) DEFAULT NULL,
  `code` VARCHAR(8) DEFAULT NULL,
  `helpCode` VARCHAR(16) DEFAULT NULL,
  `sex` CHAR(1) DEFAULT NULL,
  `age` VARCHAR(32) DEFAULT NULL,
  `price` FLOAT DEFAULT NULL,
  `remark` VARCHAR(3000) DEFAULT NULL,
  `attention` VARCHAR(128) DEFAULT NULL,
  `img` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_setmeal_travelgroup`;
CREATE TABLE `t_setmeal_travelgroup` (
  `setmeal_id` INT(11) NOT NULL DEFAULT '0',
  `travelgroup_id` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`setmeal_id`,`travelgroup_id`),
  KEY `travelgroup_key` (`travelgroup_id`),
  CONSTRAINT `travelgroup_key` FOREIGN KEY (`travelgroup_id`) REFERENCES `t_travelgroup` (`id`),
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;