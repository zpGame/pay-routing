package com.hunk.route.domain;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>路由规则 交易类型、卡类型、账户类型、银行、限额
 */
@Entity
@ToString
@Table(name = "route_rule")
public class RouteRule {

    @Id @GeneratedValue private Long id;

    private String ruleId;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ElementCollection private List<BankInfo> bankInfos;

    @Embedded private Money money;

    public RouteRule() {}

    public RouteRule(
            String ruleId,
            TradeType tradeType,
            AccountType accountType,
            List<BankInfo> bankInfos,
            Money money) {
        this.ruleId = ruleId;
        this.tradeType = tradeType;
        this.accountType = accountType;
        this.bankInfos = bankInfos;
        this.money = money;
    }

    public boolean validTradeType(TradeType tradeType) {
        return this.tradeType.equals(tradeType);
    }

    public boolean validAccountType(AccountType accountType) {
        return this.accountType.equals(accountType);
    }

    public boolean validMoney() {
        return true;
    }

    private BankInfo findBean(String bankShortName) {
        return bankInfos.stream()
                .collect(Collectors.toMap(BankInfo::getBankShortName, Function.identity()))
                .get(bankShortName);
    }
}
