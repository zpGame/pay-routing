package com.hunk.route.domain.model.bank;

import com.hunk.route.domain.model.BaseEntity;
import com.hunk.route.domain.model.CreateInfo;
import com.hunk.route.domain.model.MajorKey;
import com.hunk.route.domain.event.BankInfoEvent;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.domain.model.em.CardType;
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
public class BankInfo extends BaseEntity {

    public static ResultWithDomainEvents<BankInfo, BankInfoEvent> createBankInfo(
            BankName bankName, CardType cardType, CreateInfo createInfo) {
        BankInfo bankInfo = new BankInfo(bankName, cardType, createInfo);
        BankInfoEvent event = new BankInfoEvent(bankInfo.getBankId(), bankName, cardType);
        return new ResultWithDomainEvents<>(bankInfo, event);
    }

    @Column(name = "bank_id", length = 32, unique = true, nullable = false)
    private String bankId;

    @Embedded private BankName bankName;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private CardType cardType;

    public BankInfo() {}

    public BankInfo(BankName bankName, CardType cardType, CreateInfo createInfo) {
        this.bankId = MajorKey.getId();
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
                .append("bankId", bankId)
                .append("bankName", bankName)
                .append("cardType", cardType)
                .append("createInfo", createInfo)
                .toString();
    }
}
