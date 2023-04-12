package com.hunk.route.domain.event;

import com.hunk.route.domain.model.em.AccountType;
import com.hunk.route.domain.model.Money;
import com.hunk.route.domain.model.em.TradeType;
import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 * @author hunk
 * @date 2022/4/5
 *     <p>
 */
@Getter
public class RouteRuleEvent extends CustomEvent {

    private final String ruleId;
    private final TradeType tradeType;
    private final AccountType accountType;
    private final Money money;
    private final Set<String> bankIds;

    public RouteRuleEvent(
            String ruleId,
            TradeType tradeType,
            AccountType accountType,
            Money money,
            Set<String> bankIds) {
        this.ruleId = ruleId;
        this.tradeType = tradeType;
        this.accountType = accountType;
        this.money = money;
        this.bankIds = bankIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ruleId", ruleId)
                .append("tradeType", tradeType)
                .append("accountType", accountType)
                .append("money", money)
                .append("bankIds", bankIds)
                .toString();
    }
}
