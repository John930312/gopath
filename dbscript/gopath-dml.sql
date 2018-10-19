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

INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('b68c24200be748219a114ecfc56510b7', NULL, 'PROJECT_CATEGORY', '项目分类', NULL, '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('797312f177c547bf9970832f2bb2924c', 'b68c24200be748219a114ecfc56510b7', 'PROJECT_CATEGORY', '肿瘤类', '1', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('1fe25029aa51456fb9f5a04dacb36c44', 'b68c24200be748219a114ecfc56510b7', 'PROJECT_CATEGORY', '非肿瘤类', '2', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('996fbf3e41c34985969141b06f22491d', 'b68c24200be748219a114ecfc56510b7', 'PROJECT_CATEGORY', '日常用药', '3', '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0221be89cda011e7a9760f184c352621', NULL, 'FIT_PEOPLE', '适用人群', NULL, '4', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('02244adbcda111e7a9760f184c352621', '0221be89cda011e7a9760f184c352621', 'FIT_PEOPLE', '通用', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0225107dcda111e7a9760f184c352621', '0221be89cda011e7a9760f184c352621', 'FIT_PEOPLE', '女性', '2', '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('022a4b99cda111e7a9760f184c352621', '0221be89cda011e7a9760f184c352621', 'FIT_PEOPLE', '男性', '1', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('012638d6cd9c11e7a9760f184c352621', NULL, 'GENDER', '性别', NULL, '5', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('01273335cd9d11e7a9760f184c352621', '012638d6cd9c11e7a9760f184c352621', 'GENDER', '男', '1', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0127490fcd9d11e7a9760f184c352621', '012638d6cd9c11e7a9760f184c352621', 'GENDER', '女', '2', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0381b603cda411e7a9760f184c352621', NULL, 'SAMPLE_TYPE', '样本类型', NULL, '7', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03828b97cda411e7a9760f184c352621', '0381b603cda411e7a9760f184c352621', 'SAMPLE_TYPE', '口腔黏膜', '1', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03838b97cda411e7a9760f184c352621', '0381b603cda411e7a9760f184c352621', 'SAMPLE_TYPE', '抗凝血', '2', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03846eedcda511e7a9760f184c352621', '0381b603cda411e7a9760f184c352621', 'SAMPLE_TYPE', '其他', '3', '3', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0371d0cacda411e7a9760f184c352621', NULL, 'REPORT_PRINT_REQUIRED', '是否需要纸质报告', NULL, '6', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('03729a86cda411e7a9760f184c352621', '0371d0cacda411e7a9760f184c352621', 'REPORT_PRINT_REQUIRED', '不需要', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('0373a093cda411e7a9760f184c352621', '0371d0cacda411e7a9760f184c352621', 'REPORT_PRINT_REQUIRED', '需要（另外收费）', '1', '2', '0');

INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('29351fdc9e8e4134848b76f0620bd2fc', '844cdfda039d4dc898c91ecaf0853864', 'IS_DISCOUNT', '启用优惠', '1', '2', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('844cdfda039d4dc898c91ecaf0853864', NULL, 'IS_DISCOUNT', '优惠状态', NULL, '8', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('a6fd33a16ecd44caa7b599dabcdbbe48', '844cdfda039d4dc898c91ecaf0853864', 'IS_DISCOUNT', '取消优惠', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('f9ddf1f29ae84f509ecc286b71c6214a', NULL, 'IS_COMMON_PACKAGE', '是否推荐通用套餐', NULL, '9', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('fdd17e88083e45d5a65f875a17cfde22', 'f9ddf1f29ae84f509ecc286b71c6214a', 'IS_COMMON_PACKAGE', '否', '0', '1', '0');
INSERT INTO `ghealth_gopath_dict` (`ID`, `PARENT_ID`, `CATEGORY`, `DICT_TEXT`, `DICT_VALUE`, `SORT`, `EDITABLE`) VALUES ('fa4e33f3748f4610b7e95c2c95597fdf', 'f9ddf1f29ae84f509ecc286b71c6214a', 'IS_COMMON_PACKAGE', '是', '1', '2', '0');



#产品管理
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('23dfe856c79e5f8ea1b5e24a1e475756', NULL, 'PRODUCT', '产品管理', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('d627f028f19c4269b4f0c9933a230798', '23dfe856c79e5f8ea1b5e24a1e475756', 'PRODUCT.PRODUCT', '产品管理', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('1c5b3d1f5821554793fb4b8e3af1474c', 'd627f028f19c4269b4f0c9933a230798', 'PRODUCT.PRODUCT.CREATE', '新增产品', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('2c076e8b48365baf855eb7cc7c0c8c21', 'd627f028f19c4269b4f0c9933a230798', 'PRODUCT.PRODUCT.LIST', '产品列表', '3');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('4e92cb6db7db58448b77a04a4c3a59d7', 'd627f028f19c4269b4f0c9933a230798', 'PRODUCT.PRODUCT.MODIFY', '修改产品', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('a02025368c4952f4ae1fc9d98502358a', 'd627f028f19c4269b4f0c9933a230798', 'PRODUCT.PRODUCT.DISPLAY', '查询产品', '4');

INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('598731967c2843b6b7fd1e22db927968', '产品修改', '/product/modify.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('b797f209a0d7461ab3b31482918620ec', '产品查看', '/product/display.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('eeb1a4a5af6b474f899bd6eb3fec9f38', '产品列表', '/product/list.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('fbb5e68312674fc3a36a46322ab8d7b9', '产品新增', '/product/create.jsp');

INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('4e92cb6db7db58448b77a04a4c3a59d7', '598731967c2843b6b7fd1e22db927968');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('a02025368c4952f4ae1fc9d98502358a', 'b797f209a0d7461ab3b31482918620ec');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('2c076e8b48365baf855eb7cc7c0c8c21', 'eeb1a4a5af6b474f899bd6eb3fec9f38');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('1c5b3d1f5821554793fb4b8e3af1474c', 'fbb5e68312674fc3a36a46322ab8d7b9');

INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('62e28bd7dfba461499aedaf185f55556', NULL, '产品管理', NULL, '3', 'fa fa-leaf');
INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('8fce52781c1644ada29f89011f9c6a5b', '62e28bd7dfba461499aedaf185f55556', '产品管理', '/product/list.jsp', '1', 'fa fa-flask');

#问卷项配置
INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('e927d20f446e4402a47906cc5c92bb72', '62e28bd7dfba461499aedaf185f55556', '问卷项配置', '/questionnaire/list.jsp', '2', 'fa fa-question');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('bd64c2196eeb4c9785bc1a3dc2427d04', '23dfe856c79e5f8ea1b5e24a1e475756', 'PRODUCT.QUERTIONNAIRE', '问卷配置项', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('c8afe1ba7e264c97afa235fea738d3c1', 'bd64c2196eeb4c9785bc1a3dc2427d04', 'PRODUCT.QUERTIONNAIRE.CREATE', '新增配置项', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('fcaeea0384974458954f67627b7b3ff5', 'bd64c2196eeb4c9785bc1a3dc2427d04', 'PRODUCT.QUERTIONNAIRE.MODIFY', '修改配置项', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('bd5e2647787745afa13193196f4e6bee', 'bd64c2196eeb4c9785bc1a3dc2427d04', 'PRODUCT.QUERTIONNAIRE.LIST', '问卷配置项查询', '3');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('295f5d89a1014f2286befc2dd83e8690', 'bd64c2196eeb4c9785bc1a3dc2427d04', 'PRODUCT.QUERTIONNAIRE.DELETE', '配置项删除', '4');

INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('5b2c54944638469880698c5c9b9af392', '问卷配置项列表', '/questionnaire/list.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('8314f50421b544d19c8812bba5a315ca', '配置项新增', '/questionnaire/create.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('03e89d5ae6c84fd28a3d4180853aa201', '配置项修改', '/questionnaire/modify.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('4ff0a7dc08ac4a7eac41c07d14629d20', '配置项删除', '/questionnaire/delete.jsp');

INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('bd5e2647787745afa13193196f4e6bee', '5b2c54944638469880698c5c9b9af392');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('c8afe1ba7e264c97afa235fea738d3c1', '8314f50421b544d19c8812bba5a315ca');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('fcaeea0384974458954f67627b7b3ff5', '03e89d5ae6c84fd28a3d4180853aa201');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('295f5d89a1014f2286befc2dd83e8690', '4ff0a7dc08ac4a7eac41c07d14629d20');

