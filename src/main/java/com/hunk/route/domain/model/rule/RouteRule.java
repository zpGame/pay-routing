package com.hunk.route.domain.model.rule;

import com.hunk.route.domain.model.BaseEntity;
import com.hunk.route.domain.model.CreateInfo;
import com.hunk.route.domain.model.MajorKey;
import com.hunk.route.domain.model.Money;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.domain.event.RouteRuleEvent;
import com.hunk.route.domain.model.bank.BankInfo;
import com.hunk.route.domain.model.em.AccountType;
import com.hunk.route.domain.model.em.CardType;
import com.hunk.route.domain.model.em.TradeType;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>路由规则 交易类型、卡类型、账户类型、银行、限额
 */
@Entity
@Getter
@Table(name = "route_rule")
@org.hibernate.annotations.Table(appliesTo = "route_rule", comment = "路由规则表")
public class RouteRule extends BaseEntity {

    public static ResultWithDomainEvents<RouteRule, RouteRuleEvent> createRouteRule(
            TradeType tradeType,
            AccountType accountType,
            Set<BankInfo> bankInfos,
            Money money,
            CreateInfo createInfo) {
        RouteRule routeRule = new RouteRule(tradeType, accountType, bankInfos, money, createInfo);
        Set<String> bankIds =
                bankInfos.stream().map(BankInfo::getBankId).collect(Collectors.toSet());
        RouteRuleEvent event =
                new RouteRuleEvent(routeRule.getRuleId(), tradeType, accountType, money, bankIds);
        return new ResultWithDomainEvents<>(routeRule, event);
    }

    @Column(name = "rule_id", length = 32)
    private String ruleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 24)
    private TradeType tradeType;

    @Enumerated(EnumType.STRING)
    @Column(length = 24)
    private AccountType accountType;

    @ManyToMany
    @JoinTable(
            name = "role_link_bank",
            joinColumns = {@JoinColumn(referencedColumnName = "rule_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "bank_id")},
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Set<BankInfo> bankInfos = new HashSet<>();

    @Embedded private Money money = Money.ZERO;

    public RouteRule() {}

    public RouteRule(
            TradeType tradeType,
            AccountType accountType,
            Set<BankInfo> bankInfos,
            Money money,
            CreateInfo createInfo) {
        this.ruleId = MajorKey.getId();
        this.tradeType = tradeType;
        this.accountType = accountType;
        this.bankInfos = bankInfos;
        this.money = money;
        this.createInfo = createInfo;
    }

    public RouteRule changeTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public RouteRule changeAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public RouteRule changeCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
        return this;
    }

    public RouteRule changeBankInfos(Set<BankInfo> bankInfos) {
        this.bankInfos = bankInfos;
        return this;
    }

    public RouteRule changeMoney(Money money) {
        this.money = money;
        return this;
    }

    public boolean validTradeType(TradeType tradeType) {
        return this.tradeType.equals(tradeType);
    }

    public boolean validAccountType(AccountType accountType) {
        return this.accountType.equals(AccountType.all) || this.accountType.equals(accountType);
    }

    public boolean validMoney(Money money) {
        return this.money.equals(Money.ZERO) || this.money.isGreaterThanOrEqual(money);
    }

    public boolean bankInfosIsNull() {
        return bankInfos.isEmpty();
    }

    public BankInfo obtainBankInfo(String bankShortName, String cardType) {
        return bankInfos.stream()
                .filter(data -> data.validBankShortName(bankShortName))
                .filter(data -> data.validCardType(CardType.valueOf(cardType)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("ruleId", ruleId)
                .append("tradeType", tradeType)
                .append("accountType", accountType)
                .append("createInfo", createInfo)
                .append("bankInfos", bankInfos)
                .append("money", money)
                .toString();
    }
}
