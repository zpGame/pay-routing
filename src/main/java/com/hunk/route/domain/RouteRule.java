package com.hunk.route.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>路由规则 交易类型、卡类型、账户类型、银行、限额
 */
@Entity
@Table(name = "route_rule")
@org.hibernate.annotations.Table(appliesTo = "route_rule", comment = "路由规则表")
public class RouteRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", length = 32)
    private Long ruleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_type", length = 32)
    private TradeType tradeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", length = 32)
    private AccountType accountType;

    @ManyToMany
    @JoinTable(
            name = "role_junction_bank",
            joinColumns = {@JoinColumn(name = "sys_rule_id", referencedColumnName = "rule_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_bank_id", referencedColumnName = "id")},
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Set<BankInfo> bankInfos = new HashSet<>();

    @Embedded private Money money;

    public void setBankInfos(Set<BankInfo> bankInfos) {
        this.bankInfos = bankInfos;
    }

    public Set<BankInfo> getBankInfos() {
        return bankInfos;
    }

    public RouteRule() {}

    public static RouteRule createRouteRule(
            TradeType tradeType, AccountType accountType, Set<BankInfo> bankInfos, Money money) {
        return new RouteRule(tradeType, accountType, bankInfos, money);
    }

    public RouteRule(
            TradeType tradeType, AccountType accountType, Set<BankInfo> bankInfos, Money money) {
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

    public boolean validMoney(Money money) {
        return this.money.isGreaterThanOrEqual(money);
    }

//    private BankInfo findBean(String bankShortName) {
//        return bankInfos.stream()
//                .collect(Collectors.toMap(BankInfo::getBankShortName, Function.identity()))
//                .get(bankShortName);
//    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ruleId", ruleId)
                .append("tradeType", tradeType)
                .append("accountType", accountType)
                .append("bankInfos", bankInfos)
                .append("money", money)
                .toString();
    }
}
