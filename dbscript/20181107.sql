ALTER TABLE `ghealth_gopath_questionnaire`
MODIFY COLUMN `REMARK`  varchar(256) DEFAULT NULL COMMENT '描述' AFTER `STATUS`;