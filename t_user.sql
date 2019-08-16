/*
Navicat MySQL Data Transfer

Source Server         : 193.112.6.17
Source Server Version : 50723
Source Host           : 193.112.6.17:3306
Source Database       : ttpai_practice

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-05-05 14:58:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户密码',
  `user_nickname` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户昵称',
  `del_tag` int(4) DEFAULT '0' COMMENT '删除标记',
  `cr_date` datetime DEFAULT NULL COMMENT '添加时间',
  `op_date` datetime DEFAULT NULL COMMENT '修改时间',
  `del_date` datetime DEFAULT NULL COMMENT '删除时间',
  `remark` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
