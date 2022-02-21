package com.hunk.route.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author hunk
 * @date 2022/2/17
 * <p> 路由规则
 * 交易类型、卡类型、账户类型、银行、限额
 */
@Data
@Entity
@Table(name = "route_rule")
public class RouteRule {

    @Id
    @GeneratedValue
    private Long id;

    private String ruleId;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ElementCollection
    private List<BankInfo> bankInfos;

    @Embedded
    private Money money;

}
