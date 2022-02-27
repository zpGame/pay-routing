package com.hunk.route.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
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
        return StringUtils.isBlank(this.bankShortName) || this.bankShortName.equals(bankShortName);
    }

    public BankName changeBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public BankName changeBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bankName", bankName)
                .append("bankShortName", bankShortName)
                .toString();
    }
}
