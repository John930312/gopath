INSERT INTO `ghealth_gopath_user` (`ID`, `USERNAME`, `PASSWORD`, `ENABLED`, `NAME`, `PHONE`, `EMAIL`, `BUILTIN`, `CREATE_TIME`, `CREATOR_NAME`, `UPDATE_TIME`, `UPDATOR_NAME`, `DELETED`, `DELETE_TIME`, `DELETOR_NAME`) VALUES ('095dc185bbb54373b97fad45341aa6e7', 'admin', '$2a$10$0wxO6ifaiWn9R0yU4Uetf.zD1RCBKYI0VdGSPWWdcLXwNZaQ9ad.2', '1', '管理员', NULL, NULL, '1', '2018-10-15 08:57:18', NULL, NULL, NULL, '0', NULL, NULL);

INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('2a0eae65a73a11e8b822408d5c9494ce', '888133a4a73911e8b822408d5c9494ce', '用户管理', '/user/list.jsp', '1', 'fa-sitemap');
INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('888133a4a73911e8b822408d5c9494ce', NULL, '系统设置', '', '4', 'fa-retweet');

INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('1e75c88b728742d99ea47c3f32c2409a', NULL, '业务管理', NULL, '1', 'fa fa-reorder');
INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('7af07ce3a35a459cbb7cd247f3a6e3ac', '1e75c88b728742d99ea47c3f32c2409a', '订单列表', '/order/list.jsp', '1', 'fa fa-tasks');


# 用户、角色管理 2018-10-16
INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('e75b99dee6054ac69bd447bcff956da1', '888133a4a73911e8b822408d5c9494ce', '角色管理', '/role/list.jsp', '2', 'fa-adjust');
#authority
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('03d66ff72b1244829ae12529e07e3fea', NULL, 'SYSYTEM', '系统管理', '3');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('03ee07a97f68449d8ad2cbea19a73462', '03d66ff72b1244829ae12529e07e3fea', 'SYSTEM.USER', '用户管理', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('16dd09b171a554d0a8664adfc6e5bd9a', '03d66ff72b1244829ae12529e07e3fea', 'SYSTEM.ROLE', '角色管理', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('1182ff669d3a5cfd92de1be45dbe1fdb', '16dd09b171a554d0a8664adfc6e5bd9a', 'SYSTEM.ROLE.LIST', '查询角色', '4');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('98c9b9affb875ac2b6ad7f0f7d9f7f0c', '16dd09b171a554d0a8664adfc6e5bd9a', 'SYSTEM.ROLE.MODIFY', '修改角色', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('b07d3212efa15f2d8271163fcd7c17c3', '16dd09b171a554d0a8664adfc6e5bd9a', 'SYSTEM.ROLE.DELETE', '删除角色', '3');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('d34cb5d08b925690b6944c37643bad29', '16dd09b171a554d0a8664adfc6e5bd9a', 'SYSTEM.ROLE.CREATE', '新增角色', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('5bc4705358ef478a9af15d946c26bdca', '03ee07a97f68449d8ad2cbea19a73462', 'SYSTEM.USER.LIST', '用户查询', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('8b9b211a297e4949aa576a28a9059338', '03ee07a97f68449d8ad2cbea19a73462', 'SYSTEM.USER.MODIFY', '用户修改', '3');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('a2dc51caa049469497e54dcbd3f7ee8f', '03ee07a97f68449d8ad2cbea19a73462', 'SYSTEM.USER.CREATE', '用户新增', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('c4dc1527be9b4e11abdd67f63e5a38dc', '03ee07a97f68449d8ad2cbea19a73462', 'SYSTEM.USER.DELETE', '用户删除', '4');
#resource
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('05d84b8756fe4edd94059f6635426f04', '用户列表', '/user/list.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('5d476dba0fe84fa2a890ab7be4b4b590', '用户新增', '/user/create.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('6fb4a880d0c640faaf17d4c7ddaf347b', '角色维护', '/role/modify.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('955f5cf192aa4328853cb05e32eaac9c', '角色新增', '/role/create.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('9ee3e3205fe24f9b910d528a02f59739', '用户删除', '/user/delete.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('b0f1765def3f40eabf34f44f9c124641', '用户修改', '/user/modify.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('da4b756c26604d6d934a06b2ece6db75', '角色查看', '/role/list.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('dc133c9b88aa4dd68e04cb66678794f0', '角色删除', '/role/delete.jsp');
#authority_resource
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('5bc4705358ef478a9af15d946c26bdca', '05d84b8756fe4edd94059f6635426f04');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('a2dc51caa049469497e54dcbd3f7ee8f', '5d476dba0fe84fa2a890ab7be4b4b590');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('8b9b211a297e4949aa576a28a9059338', 'b0f1765def3f40eabf34f44f9c124641');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('c4dc1527be9b4e11abdd67f63e5a38dc', '9ee3e3205fe24f9b910d528a02f59739');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('1182ff669d3a5cfd92de1be45dbe1fdb', 'da4b756c26604d6d934a06b2ece6db75');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('d34cb5d08b925690b6944c37643bad29', '955f5cf192aa4328853cb05e32eaac9c');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('98c9b9affb875ac2b6ad7f0f7d9f7f0c', '6fb4a880d0c640faaf17d4c7ddaf347b');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('b07d3212efa15f2d8271163fcd7c17c3', 'dc133c9b88aa4dd68e04cb66678794f0');

#字典
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03419fcdcda211e7a9760f184c352621', NULL, 'IS_DISABLE', '启用状态', NULL, '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0342eaeacda211e7a9760f184c352621', '03419fcdcda211e7a9760f184c352621', 'IS_DISABLE', '禁用', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('034388c9cda211e7a9760f184c352621', '03419fcdcda211e7a9760f184c352621', 'IS_DISABLE', '启用', '1', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('032daff9cd9d11e7a9760f184c352621', NULL, 'ORDER_STATUS', '订单状态', NULL, '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0339dd67cda011e7a9760f184c352621', '032daff9cd9d11e7a9760f184c352621', 'ORDER_STATUS', '已下单', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0349dd67cda0125964760f184c352621', '032daff9cd9d11e7a9760f184c352621', 'ORDER_STATUS', '已寄送', '1', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('035e9694cda011e7a9760f184c352621', '032daff9cd9d11e7a9760f184c352621', 'ORDER_STATUS', '实验中', '2', '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('c370f7769a654967b35313d6db688a57', '032daff9cd9d11e7a9760f184c352621', 'ORDER_STATUS', '已完成', '3', '4', '0');

INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('b68c24200be748219a114ecfc56510b7', NULL, 'PROJECT_TYPE', '项目分类', NULL, '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('797312f177c547bf9970832f2bb2924c', 'b68c24200be748219a114ecfc56510b7', 'PROJECT_TYPE', '肿瘤类', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('1fe25029aa51456fb9f5a04dacb36c44', 'b68c24200be748219a114ecfc56510b7', 'PROJECT_TYPE', '非肿瘤类', '1', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('996fbf3e41c34985969141b06f22491d', 'b68c24200be748219a114ecfc56510b7', 'PROJECT_TYPE', '日常用药', '2', '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0221be89cda011e7a9760f184c352621', NULL, 'FIT_PEOPLE', '适用人群', NULL, '4', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('02244adbcda111e7a9760f184c352621', '0221be89cda011e7a9760f184c352621', 'FIT_PEOPLE', '通用', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0225107dcda111e7a9760f184c352621', '0221be89cda011e7a9760f184c352621', 'FIT_PEOPLE', '女性', '2', '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('022a4b99cda111e7a9760f184c352621', '0221be89cda011e7a9760f184c352621', 'FIT_PEOPLE', '男性', '1', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('012638d6cd9c11e7a9760f184c352621', NULL, 'GENDER', '性别', NULL, '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('01273335cd9d11e7a9760f184c352621', '012638d6cd9c11e7a9760f184c352621', 'GENDER', '男', '1', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0127490fcd9d11e7a9760f184c352621', '012638d6cd9c11e7a9760f184c352621', 'GENDER', '女', '2', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0381b603cda411e7a9760f184c352621', NULL, 'SAMPLE_TYPE', '样本类型', NULL, '7', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03828b97cda411e7a9760f184c352621', '0381b603cda411e7a9760f184c352621', 'SAMPLE_TYPE', '口腔黏膜', '1', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03838b97cda411e7a9760f184c352621', '0381b603cda411e7a9760f184c352621', 'SAMPLE_TYPE', '抗凝血', '2', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03846eedcda511e7a9760f184c352621', '0381b603cda411e7a9760f184c352621', 'SAMPLE_TYPE', '其他', '3', '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0371d0cacda411e7a9760f184c352621', NULL, 'REPORT_PRINT_REQUIRED', '是否需要纸质报告', NULL, '6', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03729a86cda411e7a9760f184c352621', '0371d0cacda411e7a9760f184c352621', 'REPORT_PRINT_REQUIRED', '不需要', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0373a093cda411e7a9760f184c352621', '0371d0cacda411e7a9760f184c352621', 'REPORT_PRINT_REQUIRED', '需要（另外收费）', '1', '2', '0');
