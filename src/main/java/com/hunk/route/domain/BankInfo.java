package com.hunk.route.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>银行信息
 */
@Entity
@Table(name = "bank_info")
@org.hibernate.annotations.Table(appliesTo = "bank_info", comment = "银行信息表")
public class BankInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_name", length = 32)
    private String bankName;

    @Column(name = "bank_short_name", length = 8)
    private String bankShortName;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", length = 12)
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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        BankInfo bankInfo = (BankInfo) obj;

        return new EqualsBuilder().append(id, bankInfo.id).isEquals();
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
