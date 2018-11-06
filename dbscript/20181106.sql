#订单权限
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('be03f8312ab54778825d9d869122bbd7', '38183c6f1a8e49e2a81f4a4406f0d053', 'OPERATION.ORDER', '订单管理', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('9f1302f209ef4496b290dbd5874bdfbf', 'be03f8312ab54778825d9d869122bbd7', 'OPERATION.ORDER.LIST', '订单列表', '1');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('ed4250cbb16b4816bf1f8a7b1baac3bc', 'be03f8312ab54778825d9d869122bbd7', 'OPERATION.ORDER.DISPLAY', '订单详情', '2');
INSERT INTO `ghealth_gopath_authority` (`ID`, `PARENT_ID`, `CODE`, `NAME`, `SORT`) VALUES ('09cd2ee407c94996af8c894e138835b2', 'be03f8312ab54778825d9d869122bbd7', 'OPERATION.ORDER.MODIFY', '订单修改', '3');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('4ecf9ee8ecdb4f78b900dc2d46783cf2', '订单列表', '/order/list.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('6fe11860a64e40c583a978c6b8771c67', '订单详情', '/order/display.jsp');
INSERT INTO `ghealth_gopath_resource` (`ID`, `NAME`, `URI`) VALUES ('5fb411977b744d299799d172940cc18b', '订单修改', '/order/modify.jsp');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('be03f8312ab54778825d9d869122bbd7', '4ecf9ee8ecdb4f78b900dc2d46783cf2');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('9f1302f209ef4496b290dbd5874bdfbf', '4ecf9ee8ecdb4f78b900dc2d46783cf2');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('ed4250cbb16b4816bf1f8a7b1baac3bc', '6fe11860a64e40c583a978c6b8771c67');
INSERT INTO `ghealth_gopath_authority_resource` (`AUTHORITY_ID`, `RESOURCE_ID`) VALUES ('09cd2ee407c94996af8c894e138835b2', '5fb411977b744d299799d172940cc18b');
