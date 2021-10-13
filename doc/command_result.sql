
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for command_result
-- ----------------------------
DROP TABLE IF EXISTS `command_result`;
CREATE TABLE `command_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_command_id` int(255) NULL DEFAULT NULL,
  `update_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of command_result
-- ----------------------------
INSERT INTO `command_result` VALUES (98, 1, '2021-10-13 15:49:16', 1, NULL);
INSERT INTO `command_result` VALUES (99, 2, '2021-10-13 15:49:18', 1, NULL);
INSERT INTO `command_result` VALUES (100, 3, '2021-10-13 15:49:20', 1, NULL);
INSERT INTO `command_result` VALUES (101, 4, '2021-10-13 15:49:22', 1, NULL);
INSERT INTO `command_result` VALUES (102, 5, '2021-10-13 15:49:24', 0, NULL);
INSERT INTO `command_result` VALUES (103, 6, '2021-10-13 15:49:26', 0, NULL);
INSERT INTO `command_result` VALUES (104, 7, '2021-10-13 15:49:41', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
