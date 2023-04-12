CREATE TABLE `bank_info`
(
    `id`              bigint      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `bank_id`         varchar(32) NOT NULL COMMENT '银行ID',
    `bank_name`       varchar(32) NOT NULL COMMENT '银行名称',
    `bank_short_name` varchar(8)  NOT NULL COMMENT '银行简称',
    `card_type`       varchar(12) DEFAULT NULL COMMENT '卡类型',
    `version`         bigint      DEFAULT NULL COMMENT '版本',
    `create_time`     datetime    DEFAULT NULL COMMENT '创建时间',
    `create_user`     varchar(32) DEFAULT NULL COMMENT '创建人',
    `modify_time`     datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_user`     varchar(32) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_bank_id` (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='银行信息表';


CREATE TABLE `channel_cost`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `route_interface` varchar(32) DEFAULT NULL COMMENT '路由接口',
    `route_name`      varchar(32) DEFAULT NULL COMMENT '路由名称',
    `rate`            varchar(12) DEFAULT NULL COMMENT '费率',
    `version`         bigint      DEFAULT NULL COMMENT '版本',
    `create_time`     datetime    DEFAULT NULL COMMENT '创建时间',
    `create_user`     varchar(32) DEFAULT NULL COMMENT '创建人',
    `modify_time`     datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_user`     varchar(32) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='渠道成本';

CREATE TABLE `merchant_link_route`
(
    `merchant_id` varchar(32) NOT NULL COMMENT '商户ID',
    `channel_id`  varchar(32) NOT NULL COMMENT '渠道ID',
    PRIMARY KEY (`merchant_id`, `channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='商户路由关联表';


CREATE TABLE `merchant_route`
(
    `id`            bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `merchant_id`   varchar(32) DEFAULT NULL COMMENT '商户ID',
    `merchant_name` varchar(56) DEFAULT NULL COMMENT '商户名称',
    `merchant_no`   varchar(32) DEFAULT NULL COMMENT '商户号',
    `version`       bigint      DEFAULT NULL COMMENT '版本',
    `create_time`   datetime    DEFAULT NULL COMMENT '创建时间',
    `create_user`   varchar(32) DEFAULT NULL COMMENT '创建人',
    `modify_time`   datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_user`   varchar(32) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_merchant_id` (`merchant_id`),
    UNIQUE KEY `uni_merchant_no` (`merchant_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='商户信息表';


CREATE TABLE `role_link_bank`
(
    `route_rule_rule_id` varchar(32) NOT NULL COMMENT '路由ID',
    `bank_infos_bank_id` varchar(32) NOT NULL COMMENT '银行信息ID',
    PRIMARY KEY (`route_rule_rule_id`, `bank_infos_bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='路由关联表';


CREATE TABLE `route_channel`
(
    `id`                bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `channel_id`        varchar(32) DEFAULT NULL COMMENT '渠道ID',
    `begin_date`        datetime    DEFAULT NULL COMMENT '开始时间',
    `end_date`          datetime    DEFAULT NULL COMMENT '结束时间',
    `is_up_hold`        int    NOT NULL COMMENT '保持',
    `route_interface`   varchar(32) DEFAULT NULL COMMENT '路由接口',
    `route_name`        varchar(32) DEFAULT NULL COMMENT '路由名称',
    `associate_rule_id` varchar(32) DEFAULT NULL COMMENT '关联规则ID',
    `version`           bigint      DEFAULT NULL COMMENT '版本',
    `create_time`       datetime    DEFAULT NULL COMMENT '创建时间',
    `create_user`       varchar(32) DEFAULT NULL COMMENT '创建人',
    `modify_time`       datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_user`       varchar(32) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_channel_id` (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='路由表';


CREATE TABLE `route_rule`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account_type` varchar(24)    DEFAULT NULL COMMENT '账户类型',
    `amount`       decimal(19, 2) DEFAULT NULL COMMENT '金额',
    `rule_id`      varchar(32)    DEFAULT NULL COMMENT '规则ID',
    `trade_type`   varchar(24)    DEFAULT NULL COMMENT '交易类型',
    `version`      bigint         DEFAULT NULL COMMENT '版本',
    `create_time`  datetime       DEFAULT NULL COMMENT '创建时间',
    `create_user`  varchar(32)    DEFAULT NULL COMMENT '创建人',
    `modify_time`  datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_user`  varchar(32)    DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_rule_id` (`rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='路由规则表';