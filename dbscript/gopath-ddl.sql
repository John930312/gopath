
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



-- 第二次添加
-- ----------------------------
-- Table structure for ghealth_gopath_customer
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_customer`;
CREATE TABLE `ghealth_gopath_customer` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `SAMPLEBOX_ID` varchar(64) NOT NULL COMMENT '采样盒',
  `AGENCY_ID` varchar(64) DEFAULT NULL COMMENT '所属代理ID',
  `NAME` varchar(64) NOT NULL COMMENT '姓名',
  `PHONE` varchar(64) NOT NULL COMMENT '联系电话',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `SEX` varchar(64) DEFAULT NULL COMMENT '性别',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '出生日期',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户主表';

-- ----------------------------
-- Table structure for ghealth_gopath_order
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_order`;
CREATE TABLE `ghealth_gopath_order` (
  `ID` varchar(64) NOT NULL,
  `CODE` varchar(64) NOT NULL COMMENT '订单编号',
  `OPEN_ID` varchar(64) NOT NULL COMMENT '微信openid',
  `PRODUCT_ID` varchar(64) NOT NULL COMMENT '产品id',
  `CUSTOMER_ID` varchar(64) DEFAULT NULL COMMENT '客户id',
  `AGENCY_ID` varchar(64) DEFAULT NULL COMMENT '代理商id',
  `SAMPLEBOX_ID` varchar(64) DEFAULT NULL COMMENT '采样盒id',
  `ACTUAL_PRICE` decimal(11,2) DEFAULT NULL COMMENT '订单价格',
  `REPORT_PRINT_REQUIRED` tinyint(1) NOT NULL COMMENT '是否需要纸质报告',
  `SAMPLE_TYPE` int(1) DEFAULT NULL,
  `STATUS` int(1) NOT NULL COMMENT '订单状态',
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `DELETED` tinyint(1) NOT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ghealth_gopath_product
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_product`;
CREATE TABLE `ghealth_gopath_product` (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `CODE` varchar(64) NOT NULL COMMENT '编号',
  `NAME` varchar(128) NOT NULL COMMENT '名称',
  `SEX_RESTRAINT` int(1) DEFAULT NULL COMMENT '性别约束',
  `GUIDING_PRICE` decimal(10,2) DEFAULT NULL COMMENT '指导价格',
  `START_TIME` datetime DEFAULT NULL COMMENT '优惠开始时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '优惠结束时间',
  `DISCOUNT_PRICE` decimal(10,2) DEFAULT NULL COMMENT '优惠价格',
  `DISCOUNT` tinyint(1) NOT NULL COMMENT '优惠标记 0-未优惠 1-已优惠',
  `ITEM_REMARK` varchar(255) DEFAULT NULL COMMENT '检测内容介绍',
  `ITEM_IDS` varchar(1024) DEFAULT NULL COMMENT '检测项目',
  `IS_COMMON_PACKAGE` tinyint(1) NOT NULL COMMENT '是否通用推荐套餐',
  `ENABLED` tinyint(1) NOT NULL COMMENT '启用状态：0-否 1-是',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '最近更新时间',
  `DELETED` tinyint(1) NOT NULL COMMENT '删除标记 0-未删除 1-已删除',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
-- Table structure for ghealth_gopath_product_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_product_questionnaire`;
CREATE TABLE `ghealth_gopath_product_questionnaire` (
  `PRODUCT_ID` varchar(64) NOT NULL,
  `QUESTIONNAIRE_ID` varchar(64) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ghealth_gopath_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_questionnaire`;
CREATE TABLE `ghealth_gopath_questionnaire` (
  `ID` varchar(64) NOT NULL,
  `NAME` varchar(64) NOT NULL,
  `CATEGORY` int(2) NOT NULL COMMENT '项目类别',
  `STATUS` tinyint(1) NOT NULL COMMENT '是否启用',
  `REMARK` varchar(128) DEFAULT NULL COMMENT '描述',
  `DELETED` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ghealth_gopath_sample_box
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_sample_box`;
CREATE TABLE `ghealth_gopath_sample_box` (
  `ID` varchar(64) NOT NULL,
  `NAME` varchar(64) NOT NULL,
  `PHONE` varchar(64) DEFAULT NULL COMMENT '收件人联系电话',
  `PROVINCE` varchar(64) DEFAULT NULL COMMENT '省',
  `CITY` varchar(64) DEFAULT NULL COMMENT '市',
  `ADDRESS` varchar(64) DEFAULT NULL COMMENT '详细地址',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采样盒';

-- ----------------------------
-- Table structure for ghealth_gopath_agency
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_agency`;
CREATE TABLE `ghealth_gopath_agency` (
  `ID` varchar(64) NOT NULL,
  `CODE` varchar(64) NOT NULL COMMENT '代理编号',
  `NAME` varchar(64) NOT NULL COMMENT '代理名称',
  `CONTACT_NAME` varchar(64) NOT NULL COMMENT '联系人',
  `PHONE` varchar(64) NOT NULL COMMENT '联系人号码',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `ghealth_gopath_sample_box`
ADD COLUMN `CODE`  varchar(64) NOT NULL AFTER `ID`;

ALTER TABLE `ghealth_gopath_order`
MODIFY COLUMN `REPORT_PRINT_REQUIRED`  int(1) NOT NULL COMMENT '是否需要纸质报告' AFTER `ACTUAL_PRICE`;

ALTER TABLE `ghealth_gopath_order`
MODIFY COLUMN `CREATE_TIME`  datetime NULL DEFAULT NULL AFTER `STATUS`,
ADD COLUMN `SAMPLING_TIME`  datetime NULL COMMENT '采样日期' AFTER `STATUS`;

ALTER TABLE `ghealth_gopath_sample_box`
ADD COLUMN `BINDED`  tinyint(1) NOT NULL COMMENT '是否已绑定' AFTER `PHONE`;

-- ----------------------------
-- Table structure for ghealth_gopath_order_history
-- ----------------------------
DROP TABLE IF EXISTS `ghealth_gopath_order_history`;
CREATE TABLE `ghealth_gopath_order_history` (
  `ID` varchar(64) NOT NULL,
  `ORDER_ID` varchar(64) NOT NULL,
  `EVENT_TYPE` int(1) NOT NULL,
  `EVENT_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ghealth_gopath_agency`;
CREATE TABLE `ghealth_gopath_agency` (
  `ID` varchar(64) NOT NULL,
  `CODE` varchar(64) NOT NULL COMMENT '代理编号',
  `NAME` varchar(64) NOT NULL COMMENT '代理名称',
  `CONTACT_NAME` varchar(64) NOT NULL COMMENT '联系人',
  `PHONE` varchar(64) NOT NULL COMMENT '联系人号码',
  `PROVINCE` varchar(64) DEFAULT NULL COMMENT '省',
  `CITY` varchar(64) DEFAULT NULL COMMENT '市',
  `ADDRESS` varchar(64) DEFAULT NULL COMMENT '详细地址',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATOR_NAME` varchar(64) DEFAULT NULL COMMENT '创建人姓名',
  `DELETED` tinyint(1) NOT NULL COMMENT '删除标记 0-未删除 1-已删除',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  `DELETOR_NAME` varchar(64) DEFAULT NULL COMMENT '删除人姓名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ghealth_gopath_slideshow`;
CREATE TABLE `ghealth_gopath_slideshow` (
  `ID` varchar(64) NOT NULL,
  `NAME` varchar(64) NOT NULL COMMENT '名称',
  `PICTURE_URL` varchar(256) NOT NULL COMMENT '图片地址',
  `CREATE_TIME` datetime DEFAULT NULL,
  `DELETED` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ghealth_gopath_slideshow_questionnaire`;
CREATE TABLE `ghealth_gopath_slideshow_questionnaire`(
  `SLIDESHOW_ID` varchar(64) NOT NULL,
  `QUESTIONNAIRE_ID` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='轮播图问卷关联表';


