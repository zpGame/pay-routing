package com.hunk.route.domain.event;

import com.hunk.route.domain.model.bank.BankName;
import com.hunk.route.domain.model.em.CardType;
import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author hunk
 * @date 2022/4/4
 *     <p>
 */
@Setter
@Getter
public class BankInfoEvent extends CustomEvent {

    private String bankId;
    private BankName bankName;
    private CardType cardType;

    public BankInfoEvent() {
    }

    public BankInfoEvent(String bankId, BankName bankName, CardType cardType) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bankId", bankId)
                .append("bankName", bankName)
                .append("cardType", cardType)
                .toString();
    }
}
