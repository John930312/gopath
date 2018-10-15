
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ghealth_gopath_authority
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_authority`;
CREATE TABLE `ghealth_gopath_authority` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(64) DEFAULT NULL,
  `CODE` varchar(128) NOT NULL COMMENT '权限编号',
  `NAME` varchar(128) NOT NULL COMMENT '权限名称',
  `SORT` int(11) NOT NULL COMMENT '排序编号',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for ghealth_gopath_authority_resource
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_authority_resource`;
CREATE TABLE `ghealth_gopath_authority_resource` (
  `AUTHORITY_ID` varchar(64) NOT NULL,
  `RESOURCE_ID` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源关联表';

-- ----------------------------
-- Table structure for ghealth_gopath_dict
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_dict`;
CREATE TABLE `ghealth_gopath_dict` (
  `ID` varchar(64) NOT NULL COMMENT '编号',
  `PARENT_ID` varchar(64) DEFAULT NULL COMMENT '上级编号',
  `CATEGORY` varchar(64) DEFAULT NULL COMMENT '类目',
  `DICT_TEXT` varchar(64) DEFAULT NULL COMMENT '显示文本',
  `DICT_VALUE` varchar(128) DEFAULT NULL COMMENT '字典值',
  `SORT` int(11) DEFAULT NULL COMMENT '排序编号',
  `EDITABLE` tinyint(1) DEFAULT NULL COMMENT '是否可编辑',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Table structure for ghealth_gopath_menu
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_menu`;
CREATE TABLE `ghealth_gopath_menu` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(64) DEFAULT NULL COMMENT '菜单上级编号',
  `NAME` varchar(64) NOT NULL COMMENT '菜单名称',
  `URI` varchar(128) DEFAULT NULL COMMENT '菜单转向链接',
  `SORT` int(11) DEFAULT NULL COMMENT '菜单排序编号',
  `ICON` varchar(64) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for ghealth_gopath_resource
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_resource`;
CREATE TABLE `ghealth_gopath_resource` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `NAME` varchar(128) NOT NULL,
  `URI` varchar(256) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Table structure for ghealth_gopath_role
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_role`;
CREATE TABLE `ghealth_gopath_role` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `NAME` varchar(32) NOT NULL COMMENT '角色名称',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATOR_NAME` varchar(64) NOT NULL COMMENT '创建人姓名',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '最近更新时间',
  `UPDATOR_NAME` varchar(64) DEFAULT NULL COMMENT '最近更新人姓名',
  `DELETED` tinyint(1) NOT NULL COMMENT '删除标记 0-未删除 1-已删除',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  `DELETOR_NAME` varchar(64) DEFAULT NULL COMMENT '删除人姓名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for ghealth_gopath_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_role_authority`;
CREATE TABLE `ghealth_gopath_role_authority` (
  `ROLE_ID` varchar(64) NOT NULL,
  `AUTHORITY_ID` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Table structure for ghealth_gopath_sequence
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_sequence`;
CREATE TABLE `ghealth_gopath_sequence` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `NAME` varchar(64) DEFAULT NULL COMMENT '序列名称',
  `CURRENT_VALUE` varchar(512) DEFAULT NULL COMMENT '当前值',
  `INCREMENT` varchar(128) DEFAULT NULL COMMENT '递增值',
  `BATCH_DATE` varchar(512) DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列号生成表';

-- ----------------------------
-- Table structure for ghealth_gopath_user
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_user`;
CREATE TABLE `ghealth_gopath_user` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `USERNAME` varchar(32) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `ENABLED` tinyint(1) NOT NULL COMMENT '账号状态',
  `NAME` varchar(32) DEFAULT NULL COMMENT '姓名',
  `PHONE` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(256) DEFAULT NULL COMMENT '电子邮箱',
  `BUILTIN` tinyint(1) NOT NULL COMMENT '是否内置账号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATOR_NAME` varchar(64) DEFAULT NULL COMMENT '创建人姓名',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '最近更新时间',
  `UPDATOR_NAME` varchar(64) DEFAULT NULL COMMENT '最近更新人姓名',
  `DELETED` tinyint(1) NOT NULL COMMENT '删除标记 0-未删除 1-已删除',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  `DELETOR_NAME` varchar(64) DEFAULT NULL COMMENT '删除人姓名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for ghealth_gopath_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_user_role`;
CREATE TABLE `ghealth_gopath_user_role` (
  `USER_ID` varchar(64) NOT NULL,
  `ROLE_ID` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for ghealth_gopath_user_token
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_user_token`;
CREATE TABLE `ghealth_gopath_user_token` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `ACCOUNT_ID` varchar(64) NOT NULL COMMENT '账号ID',
  `TOKEN` varchar(128) NOT NULL COMMENT '令牌',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EXPIRE_TIME` datetime DEFAULT NULL COMMENT '失效时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号登录token表';
