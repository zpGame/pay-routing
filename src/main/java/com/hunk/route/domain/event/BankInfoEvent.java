package com.hunk.route.domain.event;

import com.hunk.route.domain.BankName;
import com.hunk.route.domain.CardType;
import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author hunk
 * @date 2022/4/4
 *     <p>
 */
@Getter
public class BankInfoEvent extends CustomEvent {

    private final String bankInfoId;
    private final BankName bankName;
    private final CardType cardType;

    public BankInfoEvent(String bankInfoId, BankName bankName, CardType cardType) {
        this.bankInfoId = bankInfoId;
        this.bankName = bankName;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bankInfoId", bankInfoId)
                .append("bankName", bankName)
                .append("cardType", cardType)
                .toString();
    }
}
