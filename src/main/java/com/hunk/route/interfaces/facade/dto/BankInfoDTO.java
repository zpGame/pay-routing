package com.hunk.route.interfaces.facade.dto;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author hunk
 * @date 2022/3/3
 *     <p>
 */
@Getter
public final class BankInfoDTO implements Serializable {

    private final Long id;

    private final String bankName;

    private final String bankShortName;

    private final String cardType;

    public BankInfoDTO(Long id, String bankName, String bankShortName, String cardType) {
        this.id = id;
        this.bankName = bankName;
        this.bankShortName = bankShortName;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("bankName", bankName)
                .append("bankShortName", bankShortName)
                .append("cardType", cardType)
                .toString();
    }
}
