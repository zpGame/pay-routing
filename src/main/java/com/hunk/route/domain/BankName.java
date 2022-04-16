package com.hunk.route.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
@Getter
@Setter
@Embeddable
public class BankName {

    @Column(name = "bank_name", length = 32)
    private String bankName;

    @Column(name = "bank_short_name", length = 8)
    private String bankShortName;

    public BankName() {
    }

    public BankName(String bankName, String bankShortName) {
        this.bankName = bankName;
        this.bankShortName = bankShortName;
    }

    public boolean validBankShortName(String bankShortName) {
        return this.bankShortName.equals(bankShortName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bankName", bankName)
                .append("bankShortName", bankShortName)
                .toString();
    }
}
