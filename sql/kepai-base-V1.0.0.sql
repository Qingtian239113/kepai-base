/*
 Navicat Premium Data Transfer

 Source Server         : 朔创测试
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 47.96.30.85:3306
 Source Schema         : com_kepai365_road

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 03/11/2020 19:43:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_manager
-- ----------------------------
DROP TABLE IF EXISTS `auth_manager`;
CREATE TABLE `auth_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_deleted` int(4) NOT NULL DEFAULT '0',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(500) NOT NULL COMMENT '密码',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `is_super` int(4) NOT NULL DEFAULT '0' COMMENT '是否是超管，0-不是',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_k` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='权限-管理员';

-- ----------------------------
-- Table structure for auth_module
-- ----------------------------
DROP TABLE IF EXISTS `auth_module`;
CREATE TABLE `auth_module` (
  `id` varchar(10) NOT NULL COMMENT '模块id',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(4) NOT NULL DEFAULT '0',
  `name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '模块名称',
  `avatar` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '图片路径',
  `seq` int(11) NOT NULL DEFAULT '1' COMMENT '排序序号',
  `is_public` int(1) NOT NULL DEFAULT '0' COMMENT '是否公开:0->不公开;1->公开',
  `is_mobile` int(1) NOT NULL DEFAULT '0' COMMENT '是否在移动端上显示:0->不显示;1->显示;',
  `mobile_url` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '移动端访问链接',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `option` (`seq`) USING BTREE,
  KEY `is_deleted` (`is_deleted`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='权限-模块';

-- ----------------------------
-- Table structure for auth_modulesub
-- ----------------------------
DROP TABLE IF EXISTS `auth_modulesub`;
CREATE TABLE `auth_modulesub` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(4) NOT NULL DEFAULT '0',
  `module_id` varchar(10) NOT NULL DEFAULT '0' COMMENT '模块id',
  `name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '模块名称',
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `seq` int(11) NOT NULL DEFAULT '1' COMMENT '排序序号',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '1->前端模块;2->后台模块;3->两端都有',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `option` (`seq`) USING BTREE,
  KEY `is_deleted` (`is_deleted`) USING BTREE,
  KEY `mode_id` (`module_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='权限-二级模块';

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_deleted` int(4) NOT NULL DEFAULT '0',
  `name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(1000) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注说明',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `is_deleted` (`is_deleted`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='权限-角色表';

-- ----------------------------
-- Table structure for auth_role_modulesub
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_modulesub`;
CREATE TABLE `auth_role_modulesub` (
  `role_id` int(11) NOT NULL,
  `modulesub_id` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`,`modulesub_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限-角色&&二级模块';

-- ----------------------------
-- Table structure for base_system_config
-- ----------------------------
DROP TABLE IF EXISTS `base_system_config`;
CREATE TABLE `base_system_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_deleted` int(4) NOT NULL DEFAULT '0',
  `config_key` varchar(255) NOT NULL,
  `config_val` text NOT NULL,
  `config_type` int(4) NOT NULL DEFAULT '0' COMMENT '类型:0-文本,1-图片',
  `remark` varchar(500) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_key` (`config_key`(20)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='基础-系统-配置';

-- ----------------------------
-- Table structure for base_system_log_login
-- ----------------------------
DROP TABLE IF EXISTS `base_system_log_login`;
CREATE TABLE `base_system_log_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_deleted` int(4) NOT NULL DEFAULT '0',
  `manager_id` int(11) NOT NULL COMMENT '管理员id',
  `ip` varchar(100) NOT NULL COMMENT 'IP地址',
  `address` varchar(255) NOT NULL COMMENT '具体地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='基础-系统-日志-登录';

-- ----------------------------
-- Table structure for base_system_log_operation
-- ----------------------------
DROP TABLE IF EXISTS `base_system_log_operation`;
CREATE TABLE `base_system_log_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_deleted` int(4) NOT NULL DEFAULT '0',
  `manager_id` int(11) NOT NULL COMMENT '管理员id',
  `title` varchar(255) NOT NULL COMMENT '日志标题',
  `subject` varchar(500) DEFAULT NULL COMMENT '小标题',
  `params` text COMMENT '参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='基础-系统-日志-操作';

SET FOREIGN_KEY_CHECKS = 1;
