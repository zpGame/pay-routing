package com.hunk.route.domain;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>银行信息
 */
@Getter
@Entity
@Table(name = "bank_info")
@org.hibernate.annotations.Table(appliesTo = "bank_info", comment = "银行信息表")
public class BankInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded private BankName bankName;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", length = 12)
    private CardType cardType;

    @Embedded private CreateInfo createInfo;

    public static BankInfo createBankInfo(
            BankName bankName, CardType cardType, CreateInfo createInfo) {
        return new BankInfo(bankName, cardType, createInfo);
    }

    public BankInfo() {}

    public BankInfo(BankName bankName, CardType cardType, CreateInfo createInfo) {
        this.bankName = bankName;
        this.cardType = cardType;
        this.createInfo = createInfo;
    }

    public boolean validBankShortName(String bankShortName) {
        return this.bankName.validBankShortName(bankShortName);
    }

    public boolean validCardType(CardType cardType) {
        return this.cardType.equals(CardType.all) || this.cardType.equals(cardType);
    }

    public BankInfo changeBankName(BankName bankName) {
        this.bankName = bankName;
        return this;
    }

    public BankInfo changeCardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    public BankInfo changeCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
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
                .append("cardType", cardType)
                .append("createInfo", createInfo)
                .toString();
    }
}
