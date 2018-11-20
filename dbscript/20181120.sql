#采样盒
INSERT INTO `ghealth_gopath_menu` (`ID`, `PARENT_ID`, `NAME`, `URI`, `SORT`, `ICON`) VALUES ('37c0dddcc6c54cd0a1ea7d4e72d43cae', '1e75c88b728742d99ea47c3f32c2409a', '采样盒列表', '/sampleBox/list.jsp', '3', 'fa fa-archive');

INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('3c7391156c6249c3b83da03bea1ad807', '38183c6f1a8e49e2a81f4a4406f0d053', 'OPERATION.SAMPLEBOX', '采集器管理', '3');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('69c52da2537946648c161fcad8eccae1', '3c7391156c6249c3b83da03bea1ad807', 'OPERATION.SAMPLEBOX.LIST', '采集器列表', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('75547752588a4fbbbbc64ce596d1fe64', '3c7391156c6249c3b83da03bea1ad807', 'OPERATION.SAMPLEBOX.CREATE', '采集器新增', '2');

INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('7bf5d2775da24efcaa64a00e5191f5df', '采样盒列表', '/sampleBox/list.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('b8b73b66c8b343e1b3a7eda517f36f53', '采样盒新增', '/sampleBox/create.jsp');

INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('3c7391156c6249c3b83da03bea1ad807', '7bf5d2775da24efcaa64a00e5191f5df');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('69c52da2537946648c161fcad8eccae1', '7bf5d2775da24efcaa64a00e5191f5df');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('75547752588a4fbbbbc64ce596d1fe64', 'b8b73b66c8b343e1b3a7eda517f36f53');


CREATE TABLE `ghealth_gopath_sample_box` (
  `ID` varchar(64) NOT NULL,
  `CODE` varchar(64) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `PHONE` varchar(64) DEFAULT NULL COMMENT '收件人联系电话',
  `BINDED` tinyint(1) NOT NULL COMMENT '是否已绑定',
  `PROVINCE` varchar(64) DEFAULT NULL COMMENT '省',
  `CITY` varchar(64) DEFAULT NULL COMMENT '市',
  `ADDRESS` varchar(64) DEFAULT NULL COMMENT '详细地址',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采样盒';
