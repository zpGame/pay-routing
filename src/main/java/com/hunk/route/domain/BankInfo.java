package com.hunk.route.domain;

import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>银行信息
 */
@ToString
@Embeddable
public class BankInfo {

    private String bankName;

    private String bankShortName;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    public String getBankShortName() {
        return bankShortName;
    }

    public BankInfo() {}

    public BankInfo(String bankName, String bankShortName, CardType cardType) {
        this.bankName = bankName;
        this.bankShortName = bankShortName;
        this.cardType = cardType;
    }

    public boolean valid(String bankShortName, CardType cardType) {
        return validBankShortName(bankShortName) && validCardType(cardType);
    }

    public boolean validBankShortName(String bankShortName) {
        return StringUtils.isBlank(this.bankShortName) || this.bankShortName.equals(bankShortName);
    }

    public boolean validCardType(CardType cardType) {
        return this.cardType == null || this.cardType.equals(cardType);
    }

    public BankInfo changeBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public BankInfo changeBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
        return this;
    }

    public BankInfo changeCardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }
}
