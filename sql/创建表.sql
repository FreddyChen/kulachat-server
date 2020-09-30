CREATE TABLE `user` (
	`user_id` INT(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '用户id，从10000开始',
	`phone` VARCHAR(11) COMMENT '手机号码',
	`nickname` VARCHAR(32) COMMENT '用户昵称',
	`avatar` VARCHAR(255) COMMENT '用户头像',
	`gender` TINYINT(1) DEFAULT 0 COMMENT '性别：0未知 1男 2女',
	`birthday` DATE COMMENT '生日：默认1990-01-01',
	`signature` VARCHAR(50) COMMENT '个性签名',
	`province` VARCHAR(50) COMMENT '所在省',
	`city` VARCHAR(50) COMMENT '所在市',
	`completed_info` TINYINT(1) DEFAULT 0 COMMENT '是否完善资料：0未完善 1已完善'
) ENGINE = INNODB AUTO_INCREMENT 10000 DEFAULT CHARSET=utf8mb4 COMMENT '用户表';