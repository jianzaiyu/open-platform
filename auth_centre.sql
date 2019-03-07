/*
Navicat MySQL Data Transfer

Source Server         : 10.12.40.224
Source Server Version : 50636
Source Host           : 10.12.40.224:3306
Source Database       : auth_centre

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2019-03-07 18:57:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for identify
-- ----------------------------
DROP TABLE IF EXISTS `identify`;
CREATE TABLE `identify` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) unsigned DEFAULT NULL,
  `card_number` varchar(20) DEFAULT NULL,
  `card_front` varchar(150) DEFAULT NULL,
  `card_back` varchar(150) DEFAULT NULL,
  `check_state` tinyint(4) DEFAULT NULL,
  `check_note` varchar(100) DEFAULT NULL,
  `auth_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of identify
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('inner_service', 'inner_service', '{bcrypt}$2a$10$EYdy3ks6rzIj5yRav/4O5OV0VIBNcA7iAA/rsghW4wD9wYbLE5gZS', 'ALL', 'client_credentials', null, 'ROLE_CLIENT', null, null, '我是客户端', 'true');
INSERT INTO `oauth_client_details` VALUES ('ui', 'ui', '{bcrypt}$2a$10$EYdy3ks6rzIj5yRav/4O5OV0VIBNcA7iAA/rsghW4wD9wYbLE5gZS', 'ALL', 'password,refresh_token', null, 'ROLE_UI', '3600', '36000', '我是前端', 'true');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) DEFAULT NULL,
  `description` varchar(200) DEFAULT '' COMMENT '描述',
  `belong_sys` varchar(45) DEFAULT NULL,
  `create_id` int(11) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '管理员', 'openplatform', '1', '2019-03-06 23:36:31');
INSERT INTO `role` VALUES ('2', 'ROLE_USER1', '普通用户', 'openplatform', '1', '2019-03-06 23:36:57');

-- ----------------------------
-- Table structure for r_userrole
-- ----------------------------
DROP TABLE IF EXISTS `r_userrole`;
CREATE TABLE `r_userrole` (
  `ur_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) unsigned DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `create_id` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_userrole
-- ----------------------------
INSERT INTO `r_userrole` VALUES ('1', '1', '1', '1', '2019-03-06 23:37:20');
INSERT INTO `r_userrole` VALUES ('2', '1', '2', '1', '2019-03-06 23:43:35');

-- ----------------------------
-- Table structure for third_oauth
-- ----------------------------
DROP TABLE IF EXISTS `third_oauth`;
CREATE TABLE `third_oauth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) unsigned DEFAULT NULL,
  `oauth_id` varchar(64) DEFAULT NULL,
  `oauth_type` varchar(32) DEFAULT NULL,
  `key_token` varchar(128) DEFAULT NULL,
  `refresh_token` varchar(128) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of third_oauth
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `userRealName` varchar(64) DEFAULT NULL,
  `telNumber` varchar(20) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `orgId` varchar(10) DEFAULT NULL,
  `orgName` varchar(40) DEFAULT NULL,
  `enterpriseName` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `userType` tinyint(4) DEFAULT NULL COMMENT '用户类型 0:管理员，1:普通用户，2:提供者',
  `avatar` varchar(150) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0',
  `lastLoginIp` varchar(64) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$EYdy3ks6rzIj5yRav/4O5OV0VIBNcA7iAA/rsghW4wD9wYbLE5gZS', '大米', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2019-03-05 14:44:48', '2019-03-05 14:44:50');
