package com.hunk.route.domain;

import java.util.List;

/**
 * @author hunk
 * @date 2022/2/17
 * <p> 路由规则
 * 交易类型、卡类型、账户类型、银行、限额
 */
public class RouteRule {

    private String ruleId;

    private TradeType tradeType;

    private CardType cardType;

    private AccountType accountType;

    private List<BankInfo> bankInfos;

    private Money money;

}
