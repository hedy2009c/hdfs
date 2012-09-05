DROP DATABASE IF EXISTS `hdfs`;
create database hdfs;
use hdfs;
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : hdfs

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2012-06-11 17:23:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `hdfs_file`
-- ----------------------------
DROP TABLE IF EXISTS `hdfs_file`;
CREATE TABLE `hdfs_file` (
  `file_name` varchar(80) NOT NULL COMMENT '文件的名字',
  `file_id` bigint(20) NOT NULL COMMENT '文件的id，对应file_director表里的id',
  `parentid` bigint(20) NOT NULL COMMENT '文件所在目录',
  `hrefAddress` varchar(80) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小，单位为kb',
  `file_url` varchar(80) NOT NULL COMMENT '件文的url',
  `create_time` datetime DEFAULT NULL COMMENT '创建文件的时间',
  `modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `safe_level` int(11) DEFAULT '1' COMMENT '安全级别，1,2,3，分别对应低中高',
  `type` int(11) NOT NULL COMMENT 'directory: 0,doc: 1,txt: 2,image: 3,exe: 4,music: 5,rar: 6,zip: 7,html: 8,pdf: 9,undefined: 10,xls: 11',
  `user_id` bigint(20) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  `encrypt_datakey` blob DEFAULT NULL COMMENT '加密后的DES密钥',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='0,1,2,3,4,5,6,7,8,9,10,11';

-- ----------------------------
-- Records of hdfs_file
-- ----------------------------
INSERT INTO `hdfs_file` VALUES ('PI.txt', '-1766876822', '92668751', null, '4', '/admin/PI.txt', '2012-06-06 16:59:15', '2012-06-06 16:59:15', '1', '2', '28', '2014-01-01 00:00:00',null);
INSERT INTO `hdfs_file` VALUES ('FFInit.txt', '-1481127676', '248362429', null, '0', '/admin/mydoc/FFInit.txt', '2012-06-06 16:58:41', '2012-06-06 16:58:41', '1', '2', '28', '2014-01-01 00:00:00',null);
INSERT INTO `hdfs_file` VALUES ('zhiliao.txt', '-998687399', '92668751', null, '6', '/admin/zhiliao.txt', '2012-06-11 16:23:49', '2012-06-11 16:23:49', '1', '2', '28', '2014-01-01 00:00:00',null);
INSERT INTO `hdfs_file` VALUES ('nn.jpg', '-873841886', '92668751', null, '26', '/admin/nn.jpg', '2012-06-06 16:59:20', '2012-06-06 16:59:20', '1', '3', '28', '2014-01-01 00:00:00',null);
INSERT INTO `hdfs_file` VALUES ('/', '47', '-1', null, null, '/', null, null, '1', '0', '1', null,null);
INSERT INTO `hdfs_file` VALUES ('aa', '3104', '47', null, null, '/aa', '2012-06-06 17:16:03', '2012-06-06 17:16:03', null, '0', '29', null,null);
INSERT INTO `hdfs_file` VALUES ('admin', '92668751', '47', null, null, '/admin', '2012-05-31 20:47:54', '2012-05-31 20:47:54', null, '0', '28', null,null);
INSERT INTO `hdfs_file` VALUES ('mydoc', '248362429', '92668751', null, null, '/admin/mydoc', '2012-06-06 16:58:10', '2012-06-06 16:58:10', null, '0', '28', null,null);
INSERT INTO `hdfs_file` VALUES ('mybb', '2086221595', '92668751', null, null, '/admin/mybb', '2012-06-11 16:24:21', '2012-06-11 16:24:21', null, '0', '28', null,null);

-- ----------------------------
-- Table structure for `hdfs_filesystem`
-- ----------------------------
DROP TABLE IF EXISTS `hdfs_filesystem`;
CREATE TABLE `hdfs_filesystem` (
  `fs_id` int(11) NOT NULL COMMENT '文件系统的id,1代表现有的文件系统',
  `fs_url` varchar(40) NOT NULL COMMENT '文件系统的url',
  `fs_name` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`fs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hdfs_filesystem
-- ----------------------------
INSERT INTO `hdfs_filesystem` VALUES ('1', 'hdfs://localhost:8889', '本机文件系统');
INSERT INTO `hdfs_filesystem` VALUES ('2', '192.168.1.22', '2号文件系统');

-- ----------------------------
-- Table structure for `hdfs_file_delete`
-- ----------------------------
DROP TABLE IF EXISTS `hdfs_file_delete`;
CREATE TABLE `hdfs_file_delete` (
  `file_name` varchar(20) NOT NULL COMMENT '文件的名字',
  `file_id` bigint(20) NOT NULL COMMENT '文件的id，对应file_director表里的id',
  `parentid` bigint(20) NOT NULL COMMENT '文件所在目录',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小，单位为kb',
  `file_url` varchar(30) NOT NULL COMMENT '件文的url',
  `create_time` datetime DEFAULT NULL COMMENT '创建文件的时间',
  `modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `safe_level` int(11) DEFAULT '1' COMMENT '安全级别，1,2,3，分别对应低中高',
  `timing` int(11) DEFAULT '-1' COMMENT '定时时间 ,-1表示永久存储，其他时间单位为天',
  `important_level` int(11) DEFAULT '1' COMMENT '文件的重要级别，1代表普通，2重要，3非常重要',
  `fs_id` int(11) DEFAULT '1' COMMENT '应对的文件系统的ID',
  `type` int(11) NOT NULL COMMENT 'directory: 0,doc: 1,txt: 2,image: 3,exe: 4,music: 5,rar: 6,zip: 7,html: 8,pdf: 9,undefined: 10,xls: 11',
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='0,1,2,3,4,5,6,7,8,9,10,11';

-- ----------------------------
-- Records of hdfs_file_delete
-- ----------------------------

-- ----------------------------
-- Table structure for `hdfs_memory`
-- ----------------------------
DROP TABLE IF EXISTS `hdfs_memory`;
CREATE TABLE `hdfs_memory` (
  `memory_id` int(11) NOT NULL AUTO_INCREMENT,
  `priority` int(11) NOT NULL DEFAULT '5' COMMENT '权限',
  `totalmemory` int(11) NOT NULL DEFAULT '102400',
  `memoryused` int(11) NOT NULL,
  PRIMARY KEY (`memory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hdfs_memory
-- ----------------------------
INSERT INTO `hdfs_memory` VALUES ('1', '0', '10000000', '74582');
INSERT INTO `hdfs_memory` VALUES ('2', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('3', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('4', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('9', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('10', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('11', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('12', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('13', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('14', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('15', '0', '0', '0');
INSERT INTO `hdfs_memory` VALUES ('16', '2', '100000000', '0');
INSERT INTO `hdfs_memory` VALUES ('17', '2', '100000000', '0');
INSERT INTO `hdfs_memory` VALUES ('18', '1', '100000000', '0');
INSERT INTO `hdfs_memory` VALUES ('19', '2', '100000000', '0');
INSERT INTO `hdfs_memory` VALUES ('20', '2', '100000000', '0');
INSERT INTO `hdfs_memory` VALUES ('21', '2', '100000000', '0');
INSERT INTO `hdfs_memory` VALUES ('22', '2', '1000000', '0');
INSERT INTO `hdfs_memory` VALUES ('23', '2', '1000000', '0');
INSERT INTO `hdfs_memory` VALUES ('24', '2', '1000000', '68');
INSERT INTO `hdfs_memory` VALUES ('25', '2', '1000000', '0');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `memory_id` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT '1',
  `root_directory` varchar(30) DEFAULT NULL COMMENT '用户对应的根目录，对应于directory里面的目录',
  `public_key` varchar(20) DEFAULT NULL COMMENT '用户的公钥的url',
  `checkuser` int DEFAULT NULL COMMENT '核对注册用户',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('28', 'admin', 'admin', 'lin@scut.edu.cn', '1376060', '24', '2', '92668751',1,null);
INSERT INTO `users` VALUES ('29', 'aa', 'aa', 'aa@com', '833393993', '25', null, '3104',null,null);
