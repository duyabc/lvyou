/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.28 : Database - meinian
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`meinian` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `meinian`;

/*Table structure for table `t_address` */

DROP TABLE IF EXISTS `t_address`;

CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressName` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `t_address` */

insert  into `t_address`(`id`,`addressName`,`lng`,`lat`) values (1,'深圳市宝安区尚硅谷(深圳校区)','113.844656','22.632231'),(2,'深圳市世界之窗','113.97939921156106','22.540746033109636'),(3,'深圳市宝安区深圳宝安国际机场-T3航站楼','113.819598','22.631062'),(6,'深圳市北站','114.03552911268908','22.615107645742687'),(8,'深圳市光明区政府','113.94256006193123','22.754465883635367'),(9,'深圳市西部硅谷大厦','113.8444607884076','22.6320909124466'),(10,'深圳市莲花山公园','114.06519268773812','22.559256676825059'),(18,'深圳市宝安区羊台山森林公园','113.97127','22.657618'),(24,'深圳市宝安区宝安公园','113.909263','22.592267'),(26,'深圳市盐田区大梅沙海滨公园','114.314011','22.598196');

/*Table structure for table `t_member` */

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
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

/*Data for the table `t_member` */

insert  into `t_member`(`id`,`fileNumber`,`name`,`sex`,`idCard`,`phoneNumber`,`regTime`,`password`,`email`,`birthday`,`remark`) values (82,NULL,'小明','1','123456789012345671','18544279942','2019-03-08',NULL,NULL,NULL,NULL),(83,NULL,'美丽','1','123456789012345672','13412345678','2019-03-11',NULL,NULL,NULL,NULL),(84,NULL,'小星星','1','123456789012345673','18343569942','2020-02-13',NULL,NULL,NULL,NULL),(85,NULL,'小强','1','123456789012345674','18344249942','2020-02-06',NULL,NULL,NULL,NULL),(86,NULL,'红康','1','123456789012345675','18544579942','2020-01-16',NULL,NULL,NULL,NULL),(87,NULL,'大海','1','123456789012345676','18544274442','2019-11-01',NULL,NULL,NULL,NULL),(88,NULL,'小婷','1','123456789012345677','18544269942','2020-02-10',NULL,NULL,NULL,NULL),(89,NULL,'晓晓','1','123456789012345678','18544339942','2020-02-01',NULL,NULL,NULL,NULL),(90,NULL,'纯生','1','123456789012345679','18544669942','2020-05-01',NULL,NULL,NULL,NULL),(91,NULL,'闰土','1','123456789012345680','18544779942','2020-05-15',NULL,NULL,NULL,NULL),(92,NULL,'波波','2','123456789012345681','18019286521','2020-06-13',NULL,NULL,NULL,NULL);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `linkUrl` varchar(128) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `parentMenuId` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`parentMenuId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`name`,`linkUrl`,`path`,`priority`,`icon`,`description`,`parentMenuId`,`level`) values (1,'会员管理',NULL,'2',1,'fa-user-md',NULL,NULL,1),(2,'会员档案','member.html','/2-1',1,NULL,NULL,1,2),(3,'会员信息上传',NULL,'/2-2',2,NULL,NULL,1,2),(4,'会员统计',NULL,'/2-3',3,NULL,NULL,1,2),(5,'预约管理',NULL,'3',2,'fa-tty',NULL,NULL,1),(6,'分公司地址','address.html','/3-1',1,NULL,NULL,5,2),(7,'预约设置','ordersetting.html','/3-2',2,NULL,NULL,5,2),(8,'套餐管理','setmeal.html','/3-3',3,NULL,NULL,5,2),(9,'跟团游管理','checkgroup.html','/3-4',4,NULL,NULL,5,2),(10,'自由行管理','checkitem.html','/3-5',5,NULL,NULL,5,2),(11,'旅游评估',NULL,'4',3,'fa-stethoscope',NULL,NULL,1),(12,'大家一起游',NULL,'/4-1',1,NULL,NULL,11,2),(13,'统计分析',NULL,'5',4,'fa-heartbeat',NULL,NULL,1),(14,'会员数量','report_member.html','/5-1',1,NULL,NULL,13,2),(15,'系统设置',NULL,'6',5,'fa-users',NULL,NULL,1),(16,'菜单管理','menu.html','/6-1',1,NULL,NULL,15,2),(17,'权限管理','permission.html','/6-2',2,NULL,NULL,15,2),(18,'角色管理','role.html','/6-3',3,NULL,NULL,15,2),(19,'用户管理','user.html','/6-4',4,NULL,NULL,15,2),(20,'套餐占比','report_setmeal.html','/5-2',2,NULL,NULL,13,2),(21,'运营数据','report_business.html','/5-3',3,NULL,NULL,13,2);

/*Table structure for table `t_order` */

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`member_id`,`orderDate`,`orderType`,`orderStatus`,`setmeal_id`) values (1,84,'2020-02-01','微信预约','已出游',3),(2,84,'2020-02-17','微信预约','未出游',3),(3,84,'2020-02-01','微信预约','未出游',3),(4,84,'2020-02-17','微信预约','已出游',4),(5,84,'2020-02-09','微信预约','未出游',4),(6,84,'2020-02-09','微信预约','已出游',4),(7,84,'2020-02-18','微信预约','未出游',5),(8,84,'2020-02-10','微信预约','未出游',6),(9,84,'2020-02-10','微信预约','未出游',6),(10,84,'2020-02-13','微信预约','未出游',7),(17,84,'2020-02-06','微信预约','已出游',12);

/*Table structure for table `t_ordersetting` */

DROP TABLE IF EXISTS `t_ordersetting`;

CREATE TABLE `t_ordersetting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL COMMENT '约预日期',
  `number` int(11) DEFAULT NULL COMMENT '可预约人数',
  `reservations` int(11) DEFAULT NULL COMMENT '已预约人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=838 DEFAULT CHARSET=utf8;

/*Data for the table `t_ordersetting` */

insert  into `t_ordersetting`(`id`,`orderDate`,`number`,`reservations`) values (736,'2020-12-01',100,0),(737,'2020-12-02',100,0),(738,'2020-12-03',100,0),(739,'2020-12-04',100,0),(740,'2020-12-05',100,0),(741,'2020-12-06',100,0),(742,'2020-12-07',100,0),(743,'2020-12-08',100,0),(744,'2020-12-09',100,0),(745,'2020-12-10',100,0),(746,'2020-12-11',100,0),(747,'2020-12-12',100,0),(748,'2020-12-13',100,0),(749,'2020-12-14',100,0),(750,'2020-12-15',100,0),(751,'2020-12-16',100,0),(752,'2020-12-17',100,0),(753,'2020-12-18',100,0),(754,'2020-12-19',100,0),(755,'2020-12-20',100,1),(756,'2020-12-21',100,0),(757,'2020-12-22',100,3),(758,'2020-12-23',100,1),(759,'2020-12-24',100,0),(760,'2020-12-25',100,0),(761,'2020-12-26',100,0),(762,'2020-12-27',100,0),(763,'2020-12-28',100,0),(764,'2020-12-29',100,0),(765,'2020-12-30',100,0),(766,'2020-12-31',100,0),(767,'2021-01-01',100,0),(768,'2021-01-02',100,0),(769,'2021-01-03',100,0),(770,'2021-01-04',100,0),(771,'2021-01-05',100,0),(772,'2021-01-06',100,0),(773,'2021-01-07',100,0),(774,'2021-01-08',100,0),(775,'2021-01-09',100,0),(776,'2021-01-10',100,0),(777,'2021-01-11',100,0),(778,'2021-01-12',100,0),(779,'2021-01-13',100,0),(780,'2021-01-14',100,0),(781,'2021-01-15',100,0),(782,'2021-01-16',100,0),(783,'2021-01-17',100,1),(785,'2021-01-19',100,0),(786,'2021-01-20',100,0),(787,'2021-01-21',100,0),(788,'2021-01-22',100,0),(789,'2021-01-23',100,0),(790,'2021-01-24',100,0),(791,'2021-01-25',100,0),(792,'2021-01-26',100,0),(793,'2021-01-27',100,0),(794,'2021-01-28',100,0),(795,'2021-01-29',100,0),(796,'2021-01-30',100,0),(797,'2021-01-31',100,0),(798,'2021-02-01',100,0),(799,'2021-02-02',100,0),(800,'2021-02-03',100,0),(801,'2021-02-04',100,0),(802,'2021-02-05',100,0),(803,'2021-02-06',100,0),(804,'2021-02-07',100,0),(805,'2021-02-08',100,0),(806,'2021-02-09',100,0),(807,'2021-02-10',100,0),(808,'2021-02-11',100,0),(809,'2021-02-12',100,0),(810,'2021-02-13',100,0),(811,'2021-02-14',100,0),(812,'2021-02-15',100,0),(813,'2021-02-16',100,0),(814,'2021-02-17',100,0),(815,'2021-02-18',100,0),(816,'2021-02-19',100,0),(817,'2021-02-20',100,0),(818,'2021-02-21',100,0),(819,'2021-02-22',100,0),(820,'2021-02-23',100,0),(821,'2021-02-24',100,0),(822,'2021-02-25',100,0),(823,'2021-02-26',100,0),(824,'2021-02-27',100,0),(825,'2021-02-28',100,0),(826,'2021-03-01',100,0),(827,'2021-03-02',100,0),(828,'2021-03-03',100,0),(829,'2021-03-04',100,0),(830,'2021-03-05',100,0),(831,'2021-03-06',100,0),(832,'2021-03-07',100,0),(833,'2021-03-08',100,0),(834,'2021-03-09',100,0),(836,'2021-03-10',22,0),(837,'2021-03-11',32,0);

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`name`,`keyword`,`description`) values (1,'新增自由行','TRAVELITEM_ADD',NULL),(2,'删除自由行','TRAVELITEM_DELETE',NULL),(3,'编辑自由行','TRAVELITEM_EDIT',NULL),(4,'查询自由行','TRAVELITEM_QUERY',NULL),(5,'新增跟团游','TRAVELGROUP_ADD',NULL),(6,'删除跟团游','TRAVELGROUP_DELETE',NULL),(7,'编辑跟团游','TRAVELGROUP_EDIT',NULL),(8,'查询跟团游','TRAVELGROUP_QUERY',NULL),(9,'新增套餐','SETMEAL_ADD',NULL),(10,'删除套餐','SETMEAL_DELETE',NULL),(11,'编辑套餐','SETMEAL_EDIT',NULL),(12,'查询套餐','SETMEAL_QUERY',NULL),(13,'预约设置','ORDERSETTING',NULL),(14,'查看统计报表','REPORT_VIEW',NULL),(15,'新增菜单','MENU_ADD',NULL),(16,'删除菜单','MENU_DELETE',NULL),(17,'编辑菜单','MENU_EDIT',NULL),(18,'查询菜单','MENU_QUERY',NULL),(19,'新增角色','ROLE_ADD',NULL),(20,'删除角色','ROLE_DELETE',NULL),(21,'编辑角色','ROLE_EDIT',NULL),(22,'查询角色','ROLE_QUERY',NULL),(23,'新增用户','USER_ADD',NULL),(24,'删除用户','USER_DELETE',NULL),(25,'编辑用户','USER_EDIT',NULL),(26,'查询用户','USER_QUERY',NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`keyword`,`description`) values (1,'系统管理员','ROLE_ADMIN',NULL),(2,'导游','ROLE_HEALTH_MANAGER',NULL);

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_Reference_10` (`menu_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_menu` */

insert  into `t_role_menu`(`role_id`,`menu_id`) values (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21);

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_Reference_12` (`permission_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`role_id`,`permission_id`) values (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(2,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12),(2,12),(1,13),(2,13),(1,14),(2,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26);

/*Table structure for table `t_setmeal` */

DROP TABLE IF EXISTS `t_setmeal`;

CREATE TABLE `t_setmeal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) DEFAULT NULL,
  `code` varchar(8) DEFAULT NULL,
  `helpCode` varchar(16) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `remark` varchar(3000) DEFAULT NULL,
  `attention` varchar(128) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `t_setmeal` */

insert  into `t_setmeal`(`id`,`name`,`code`,`helpCode`,`sex`,`age`,`price`,`remark`,`attention`,`img`) values (3,'澳门威尼斯人商圈酒店双飞3-5日自由行套餐','0001','RZTJ','2','35-55',300,'澳门巴黎人是全澳门乃至亚洲的一颗闪耀新星，您可以在此感受“光之城”巴黎的独特艺术气息与迷人魅力。这里有约2千余间法式客房及套房供您选择，还有依照巴黎埃菲尔铁塔1/2比例建造的巴黎铁塔，让您体验独特的浪漫风情。您还可以于170家精品名店享受购物乐趣，或是品尝经典法式美食，欣赏精彩的娱乐表演，畅游水世界、儿童王国等各种娱乐项目，像巴黎人一样体验无处不在的浪漫与惊喜！',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(4,'香港九龙尖沙咀商圈双飞3-8日自由行套餐','0001','RZTJ','0','22-45',300,'香港九龙珀丽酒店(Rosedale Hotel Kowloon)位于市中心繁华闹市，地处九龙中心地带，毗邻多个购物、娱乐中心；酒店提供班车来往旺角、尖沙嘴和“圆方”购物区，方便宾客出行。 香港九龙珀丽酒店(Rosedale Hotel Kowloon) 设计精巧、新颖独特，拥有精致、高雅的各式客房。酒店客房均配有LED智能电视、iPod/ iPhone 底座，让您尽享便捷、舒适生活。',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(5,'海南-亚龙湾-三亚双飞7日游','0001','RZTJ','0','18-60',300,'全程由旅游局指定餐厅用餐，严格把控餐饮质量，让您安心享用每一顿饮食，尽情尝特色美食，让味蕾绽放在路上，品味舌尖上的海南 。',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(6,'丽江-大理-香格里拉双飞6日游','0001','RZTJ','0','44-64',300,'云南地处高原，老年人体温调节功能较差，易受凉感冒，所以衣服要带得够，以便随时增减，行走出汗时，不要马上脱衣敞怀。高原地区昼夜温差大，睡前要盖好被毯，夜间风起雨来时要关好门窗。',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(7,'海南-亚龙湾-三亚双飞8日游','0001','RZTJ','0','33-76',300,'旅游中要有充足的休息和睡眠，若感到体力不支，可略着休息或减缓旅行。在长时间步行游览时应随时坐下小憩。',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(12,'公司年度旅游套餐（男女通用）','0001','RZTJ','0','16-33',300,'公司年度旅游套餐',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(13,'广州长隆酒店+珠海长隆企鹅酒店双飞5日自由行套餐','0002','FHZA','2','25-44',1200,'长隆酒店(广州长隆野生动物世界店)位于广州长隆旅游度假区中心地段，毗邻长隆欢乐世界、长隆水上乐园、长隆野生动物世界、长隆飞鸟乐园和长隆国际大马戏等主题乐园。交通便捷，多条园区穿梭巴往返长隆酒店和各大园区之间。珠海园区、机场快线、香港直通巴汇集其中，广州地铁3号线/7号线让您的度假娱乐更快捷方便。 长隆酒店作为大型的生态主题酒店，主要以热带区域的人文文化风情为主基调。客房的摆设装修以客为主，温馨舒适，并有多种客房和套房类型选择。酒店绿植围绕，种类繁多，动物岛置身于酒店之中，珍稀动物随处可见。 酒店内配套多间风味各异的餐厅，让你足不出户品尝环球美食。酒店配套室外泳池、室内四季恒温泳池、健身房、童趣乐园、康体中心等，都是您商旅或者度假的上佳消遣地方。 国际会展中心更有可容纳3,000人的6,000平方米宴会厅，拥有12米无柱高楼顶，配备LED屏幕，及大型内置8米升降舞台、先进的多媒体视听、通讯系统等商务会议设施。39个不同规格的豪华多功能会议厅。专业的销售、服务团队和完善的会议设施设备随时准备为您提供一流的会议、展览和宴会服务，打造一流的商务会议品牌。',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(14,'厦门+鼓浪屿双飞5日自由行套餐','0003','YGBM','0','55-100',1400,'鼓浪屿的生活，是慵懒而优雅的，像极了欧洲某个古老的城市，不张扬，却有着致命的吸引力——温柔的阳光，蔚蓝的大海，美妙的琴声，静静矗立的老建筑，悠然自得的猫咪，所有的一切，总让人忍不住想好好的，再谈一场恋爱。 　　所以做了这样的主题，就是想把所谓浪漫，狠狠地，彻底地，进行下去。要华丽，因为青春理应肆意和张扬；要低调，因为在心底，总有一个最柔软最隐秘的角落，是只属于你的。最重要的，是要浪漫。在流淌着音乐的房间里，看点点烛光摇曳，撒在床上的玫瑰花瓣香味隐约扑来，举起手中的红酒杯，两个人，相视无语。cheers. 感谢你们，与我们分享这美好。也但愿我能，一直见证你们的幸福。',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg'),(15,'云南-昆明-大理-丽江-香格里拉双飞8日游套餐','0004','ZAGD','0','14-20',2400,'今日行程无导游陪同，如您当日抵昆时间较早，可自行将行李寄存在入住酒店（贵重物品请自行保管好）；随后，自由活动（如抵达昆明时间尚早，可自行前往云南师范大学（西南联合大学旧址）、昆明金马碧鸡坊、南屏街、、陆军讲武堂等景点游览、（外出酒店时贵重物品请自行保管好，请到酒店前台带上酒店名片，方便打车回酒店）',NULL,'393d64ed-40fb-4595-8d0f-33662f70ede5.jpg');

/*Table structure for table `t_setmeal_travelgroup` */

DROP TABLE IF EXISTS `t_setmeal_travelgroup`;

CREATE TABLE `t_setmeal_travelgroup` (
  `setmeal_id` int(11) NOT NULL DEFAULT '0',
  `travelgroup_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`setmeal_id`,`travelgroup_id`),
  KEY `travelgroup_key` (`travelgroup_id`),
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`),
  CONSTRAINT `travelgroup_key` FOREIGN KEY (`travelgroup_id`) REFERENCES `t_travelgroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_setmeal_travelgroup` */

insert  into `t_setmeal_travelgroup`(`setmeal_id`,`travelgroup_id`) values (3,5),(12,5),(3,6),(12,6),(3,7),(12,7),(3,8),(12,8),(3,9),(12,9),(3,10),(4,10),(12,10),(14,10),(15,10),(3,11),(4,11),(5,11),(12,11),(14,11),(15,11),(4,12),(14,12),(4,13),(5,13),(14,13),(15,13),(5,14),(6,14),(13,14),(15,14),(6,15),(13,15);

/*Table structure for table `t_travelgroup` */

DROP TABLE IF EXISTS `t_travelgroup`;

CREATE TABLE `t_travelgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `helpCode` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `attention` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `t_travelgroup` */

insert  into `t_travelgroup`(`id`,`code`,`name`,`helpCode`,`sex`,`remark`,`attention`) values (5,'0001','昆明、大理、丽江双飞单动6日经典游','YBJC','0','春节昆明-大理-丽江双飞6日游牛新品/18人小团0购物+5A石林+玉龙雪山大索登顶+洱海Jeep旅拍+南诏风情岛+非遗白族扎染体验+彝族特色长街宴 ','111'),(6,'0002','三亚-蜈支洲岛-亚龙湾双飞5日4晚跟团游','SLSJ','0','华东五市-乌镇-南浔-杭州双飞6日游>全新升级4晚维也纳系列酒店，全程0自费，观水乡婚礼，登雷峰塔，品金陵十六味，含宋城/上海夜景','22'),(7,'0003','重庆、武隆仙女山、天坑三硚、龙水峡地缝、洪崖洞、磁器口古镇双飞5日经典游','XCG','0','春节南宁-越南-下龙湾-北海双飞7日游>0购0自费/境外国际4星/1晚希尔顿/168元海鲜自助/奥巴马米粉/赠越式按摩/可升涠洲岛/全程领队/双口岸进出','233'),(8,'0004','印度尼西亚巴厘岛7日5晚半自助跟团游','NCG','0','春节海南三亚双飞5日游17万人游玩0购物，180度海景房(华美达/明申/维景/唐拉雅秀)，赠全海景玻璃栈道+蟹鲍海鲜餐，20年出游去南山看108米海上观音','44'),(9,'0005','厦门-鼓浪屿-云水谣土楼双飞5日4晚跟团游','GGSX','0','厦门-鼓浪屿双飞5日游臻品9人团0购0自，全程五星华邑/万豪海景房，高标佳丽自助/地标全海景餐厅，人气日光岩植物园鹭江夜游，接送0等待','55'),(10,'0006','越南芽庄6日5晚自由行','NGSX','0','土耳其10日游超6000人选择牛人专线/五星航空直飞内陆双飞/费特希耶/网红景点齐打卡/五星酒店升1晚真洞穴酒店/升级4特色餐/2顿中餐','66'),(11,'0007','印度尼西亚巴厘岛7日5晚半自助跟团游','XZSX','0','春节埃及-迪拜-阿布扎比10日游A380或波音787/埃及全程五星/红海两晚/宿卢克索/吉萨金字塔群/埃及博物馆/马车巡游/风帆船',NULL),(12,'0008','泰国普吉岛7日5晚跟团游','XJMSX','0','春节埃及+土耳其11-14日游埃及卢克索/亚历山大/红海,土耳其伊斯坦布尔/圣索菲亚大教堂,内陆4段飞,含卡帕和棉花堡,TK',NULL),(13,'0009','悉尼-黄金海岸 汉密尔顿8日6晚品质游','JGSX','0','南美印象5国+巴西+阿根廷+秘鲁+智利+乌拉圭16-20日游>纯玩无购物/雨林/伊瓜苏瀑布/纳斯卡地画/四到五星酒店/部分增游马丘比丘',NULL),(14,'0010','柬埔寨吴哥6日4晚跟团游','ZGFJCC','2','春节美国东西海岸11-13日游游多款产品可选/迪士尼一日双园过大年/环球影城/峡谷/羚羊彩穴/马蹄湾/部分团期升级纯玩团/保证拼住',NULL),(15,'0011','张家界、玻璃桥、天门山、芙蓉镇、凤凰古城双飞5日跟团游','DHSSX','0','美国西雅图-波特兰6晚8日自由行西雅图进波特兰出，精选全程6晚酒店，行程天数可随意增减',NULL);

/*Table structure for table `t_travelgroup_travelitem` */

DROP TABLE IF EXISTS `t_travelgroup_travelitem`;

CREATE TABLE `t_travelgroup_travelitem` (
  `travelgroup_id` int(11) NOT NULL DEFAULT '0',
  `travelitem_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`travelgroup_id`,`travelitem_id`),
  KEY `item_id` (`travelitem_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`travelgroup_id`) REFERENCES `t_travelgroup` (`id`),
  CONSTRAINT `item_id` FOREIGN KEY (`travelitem_id`) REFERENCES `t_travelitem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_travelgroup_travelitem` */

insert  into `t_travelgroup_travelitem`(`travelgroup_id`,`travelitem_id`) values (5,31),(5,32),(6,33),(6,34);

/*Table structure for table `t_travelitem` */

DROP TABLE IF EXISTS `t_travelitem`;

CREATE TABLE `t_travelitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `type` char(1) DEFAULT NULL COMMENT '自由行类型,分为自由和跟团两种',
  `attention` varchar(128) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

/*Data for the table `t_travelitem` */

insert  into `t_travelitem`(`id`,`code`,`name`,`sex`,`age`,`price`,`type`,`attention`,`remark`) values (31,'0004','三亚大东海风景迷人5日家庭游','1','0-100',5,'1','无','三亚大东海风景迷人5日家庭游'),(32,'0005','4天3晚厦门玩法，热恋在土楼与都市沿岸','0','0-100',5,'1','无','4天3晚厦门玩法，热恋在土楼与都市沿岸'),(33,'0006','4日3晚，厦门亲子玩法','0','0-100',5,'1','无','4日3晚，厦门亲子玩法'),(34,'0007','厦门风灌醉鼓浪屿的海4日浪漫游','0','0-100',5,'1','无','厦门风灌醉鼓浪屿的海4日浪漫游'),(35,'0008','4天3晚，海上花园城市，厦门玩法','0','0-100',5,'1','无','4天3晚，海上花园城市，厦门玩法'),(36,'0009','香港迪士尼里追童梦5日亲子游','0','0-100',5,'1','无','香港迪士尼里追童梦5日亲子游'),(37,'0010','香港铜锣湾处处风情5日休闲游','0','0-100',5,'1','无','香港铜锣湾处处风情5日休闲游'),(38,'0011','5天4晚寻味香港玩法，回忆记忆中的“港味儿”','0','0-100',10,'2','无','5天4晚寻味香港玩法，回忆记忆中的“港味儿”'),(39,'0012','版纳傣族园丽江古城8日三地连线游','0','0-100',10,'2',NULL,'版纳傣族园丽江古城 8日三地连线游'),(40,'0013','6天5晚，丽江香格里拉蜜月玩法，意外的遇见，刚刚好！','0','0-100',10,'2',NULL,'6天5晚，丽江香格里拉蜜月玩法，意外的遇见，刚刚好！'),(41,'0014','4天3晚，云南丽江大众玩法','0','0-100',10,'2',NULL,'4天3晚，云南丽江大众玩法'),(42,'0015','丽江古城佳肴诱惑5日美食游','0','0-100',10,'2',NULL,'丽江古城佳肴诱惑5日美食游'),(43,'0016','成都九寨沟五彩天堂7日深度游','0','0-100',10,'2',NULL,'成都九寨沟五彩天堂7日深度游'),(44,'0017','成都九寨沟黄龙远离喧嚣6日风光游','0','0-100',10,'2',NULL,'成都九寨沟黄龙远离喧嚣6日风光游'),(45,'0018','7天6晚，四川玩法','0','0-100',10,'2',NULL,'7天6晚，四川玩法'),(46,'0019','4天3晚，大连玩法，阳光明媚去看海','0','0-100',10,'2',NULL,'4天3晚，大连玩法，阳光明媚去看海'),(47,'0020','4天3晚，浪漫之都大连玩法','0','0-100',10,'2',NULL,'4天3晚，浪漫之都大连玩法'),(48,'0021','4天3晚大连海滨玩法','0','0-100',10,'2',NULL,'4天3晚大连海滨玩法'),(49,'0022','大连美丽不过滨海路 4日经典游','0','0-100',10,'2',NULL,'大连美丽不过滨海路4日经典游'),(50,'0023','1天，尽览东方巴黎风采，哈尔滨玩法','0','0-100',10,'2',NULL,'1天，尽览东方巴黎风采，哈尔滨玩法'),(51,'0024','5天4晚哈尔滨+吉林双城玩法','0','0-100',10,'2',NULL,'5天4晚哈尔滨+吉林双城玩法'),(52,'0025','哈尔滨老道外风情建筑 5日魅力游','0','0-100',10,'2',NULL,'哈尔滨老道外风情建筑5日魅力游'),(53,'0026','哈尔滨雪博会一头栽雪堆 5日狂欢游','0','0-100',10,'2',NULL,'哈尔滨雪博会一头栽雪堆5日狂欢游'),(54,'0027','大阪东京夜景艾特你了6日连线游','0','0-100',10,'2',NULL,'大阪东京夜景艾特你了6日连线游'),(55,'0028','东京大阪一秒钟嗨翻乐园 6日亲子游','0','0-100',10,'2',NULL,'东京大阪一秒钟嗨翻乐园6日亲子游'),(56,'0029','东京扫货扫到手抽筋5日购物游','0','0-100',10,'2',NULL,'东京扫货扫到手抽筋5日购物游'),(57,'0030','东京富士山温泉5日泡汤游','0','0-100',10,'2',NULL,'东京富士山温泉 5日泡汤游'),(58,'0031','巴厘岛梦幻海滩分享甜蜜 5日蜜月游','0','0-100',10,'2',NULL,'巴厘岛梦幻海滩分享甜蜜5日蜜月游'),(59,'0032','6天5晚千岛之国，万般风情，印尼海岛玩法。','0','0-100',10,'2',NULL,'6天5晚千岛之国，万般风情，印尼海岛玩法。'),(60,'0033','5天4晚巴厘岛玩法，拥抱理想国的阳光','0','0-100',10,'2',NULL,'5天4晚巴厘岛玩法，拥抱理想国的阳光'),(61,'0034','巴厘岛金巴兰海滩享SPA6日悠闲游','0','0-100',10,'2',NULL,'巴厘岛金巴兰海滩享SPA6日悠闲游'),(62,'0035','巴厘岛乌布皇宫如梦如幻 5日蜜月游','0','0-100',10,'2',NULL,'巴厘岛乌布皇宫如梦如幻 5日蜜月游'),(63,'0036','巴厘岛海神庙全家出行8日亲子游','0','0-100',10,'2',NULL,'巴厘岛海神庙全家出行8日亲子游'),(64,'0037','巴厘岛金银岛悠然自得6日经典游','0','0-100',10,'2',NULL,'巴厘岛金银岛悠然自得6日经典游'),(65,'0038','巴厘岛蓝点教堂SPA专享7日休闲游','0','0-100',10,'2',NULL,'巴厘岛蓝点教堂SPA专享7日休闲游'),(66,'0039','大阪东京夜景艾特你了6日连线游','0','0-100',10,'2',NULL,'大阪东京夜景艾特你了6日连线游'),(67,'0040','东京大阪一秒钟嗨翻乐园6日亲子游','0','0-100',10,'2',NULL,'东京大阪一秒钟嗨翻乐园6日亲子游'),(68,'0041','6天5晚最牛的日本本州玩法','0','0-100',10,'2',NULL,'6天5晚最牛的日本本州玩法'),(69,'0042','日本京都箱根东瀛和风7日风情游','0','0-100',10,'2',NULL,'日本京都箱根东瀛和风7日风情游'),(70,'0043','日本箱根大涌谷6日关东关西游','0','0-100',10,'2',NULL,'日本箱根大涌谷6日关东关西游'),(71,'0044','日本伏见稻荷朱红色6日古都游','0','0-100',10,'2',NULL,'日本伏见稻荷朱红色6日古都游'),(72,'0045','大阪环球影城一键收藏5日亲子游','0','0-100',10,'2',NULL,'大阪环球影城一键收藏5日亲子游'),(73,'0046','大阪黑门市场吃海鲜5日美食游','0','0-100',10,'2',NULL,'大阪黑门市场吃海鲜5日美食游'),(74,'0047','大阪城公园樱花美成画5日经典游','0','0-100',10,'2',NULL,'大阪城公园樱花美成画5日经典游'),(75,'0048','大阪海游馆亲密接触5日休闲游','0','0-100',10,'2',NULL,'大阪海游馆亲密接触5日休闲游'),(76,'0049','日本奈良公园小鹿乱撞7日古都游','0','0-100',10,'2',NULL,'日本奈良公园小鹿乱撞7日古都游'),(77,'0050','大阪道顿堀小吃嘴不停6日美食游','0','0-100',10,'2',NULL,'大阪道顿堀小吃嘴不停6日美食游'),(78,'0051','大阪梅田空中庭院漫步6日休闲游','0','0-100',10,'2',NULL,'大阪梅田空中庭院漫步6日休闲游'),(79,'0052','大阪奈良赴一场盛宴6日深度游','0','0-100',10,'2',NULL,'大阪奈良赴一场盛宴6日深度游'),(80,'0053','日本繁华都市闲逛6日深度游','0','0-100',10,'2',NULL,'日本繁华都市闲逛6日深度游'),(81,'0054','毛里求斯鹿岛享沙滩阳光6日蜜月游','0','0-100',10,'2',NULL,'毛里求斯鹿岛享沙滩阳光6日蜜月游'),(82,'0055','7天6晚毛里求斯360度多情与激情玩法','0','0-100',10,'2',NULL,'7天6晚毛里求斯360度多情与激情玩法'),(83,'0056','毛里求斯迪拜享浪漫10日蜜月游','0','0-100',10,'2',NULL,'毛里求斯迪拜享浪漫10日蜜月游'),(84,'0057','毛里求斯迪拜两国畅玩9日经典游','0','0-100',10,'2',NULL,'毛里求斯迪拜两国畅玩9日经典游'),(85,'0058','毛里求斯蓝湾纯净浪漫8日蜜月游','0','0-100',10,'2',NULL,'毛里求斯蓝湾纯净浪漫8日蜜月游'),(86,'0059','毛里求斯七色土绚烂多姿8日蜜月游','0','0-100',10,'2',NULL,'毛里求斯七色土绚烂多姿8日蜜月游'),(87,'0060','5天4晚四川成都大众玩法','0','0-100',10,'2',NULL,'5天4晚四川成都大众玩法'),(88,'0061','成都杜甫草堂历史悠久5日访古游','2','0-100',10,'2',NULL,'成都杜甫草堂历史悠久5日访古游'),(89,'0062','成都人民公园享欢乐时光5日亲子游','2','0-100',10,'2',NULL,'成都人民公园享欢乐时光5日亲子游'),(90,'0063','成都锦里小吃很地道4日美食游','0','0-100',10,'2',NULL,'成都锦里小吃很地道4日美食游'),(91,'0064','成都锦里感受慢生活4日经典游','0','0-100',10,'2',NULL,'成都锦里感受慢生活4日经典游'),(92,'0065','成都春熙路吃货天堂4日美食游','0','0-100',10,'2',NULL,'成都春熙路吃货天堂4日美食游');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `remark` varchar(32) DEFAULT NULL,
  `station` varchar(1) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`birthday`,`gender`,`username`,`password`,`remark`,`station`,`telephone`) values (1,NULL,NULL,'admin','$2a$10$IIzBTSMW7rSSFF7fAUTjyO8CPL2CKAUHA1dOzBvKNLy1qwagDj/pu',NULL,NULL,NULL),(2,NULL,NULL,'xiaoming','$2a$10$yQF1gPy6EMws.PJ2rnnsaOa9l/LlrBpBTJi7k85E4oNIissAddUcu',NULL,NULL,NULL),(3,NULL,NULL,'test','$2a$10$yQF1gPy6EMws.PJ2rnnsaOa9l/LlrBpBTJi7k85E4oNIissAddUcu',NULL,NULL,NULL);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_Reference_8` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user_id`,`role_id`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
