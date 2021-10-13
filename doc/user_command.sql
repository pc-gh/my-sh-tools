
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_command
-- ----------------------------
DROP TABLE IF EXISTS `user_command`;
CREATE TABLE `user_command`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` int(255) NULL DEFAULT NULL,
  `command` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `port` int(255) NULL DEFAULT NULL,
  `threshold` int(255) NULL DEFAULT NULL,
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `item` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_command
-- ----------------------------
INSERT INTO `user_command` VALUES (1, 1, 'ping', '', NULL, NULL, 'www.baidu.com', NULL, NULL);
INSERT INTO `user_command` VALUES (2, 2, 'ps', NULL, NULL, 5, NULL, 'systemctl', NULL);
INSERT INTO `user_command` VALUES (3, 3, 'telnet', NULL, 80, NULL, 'baidu.com', NULL, NULL);
INSERT INTO `user_command` VALUES (4, 4, 'curl', 'www.baidu.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_command` VALUES (5, 1, 'ping', NULL, NULL, NULL, 'dsdsds.cs', NULL, NULL);
INSERT INTO `user_command` VALUES (6, 2, 'ps', NULL, NULL, 4, NULL, 'sh', NULL);
INSERT INTO `user_command` VALUES (7, 1, 'ping', NULL, NULL, NULL, 'google.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
