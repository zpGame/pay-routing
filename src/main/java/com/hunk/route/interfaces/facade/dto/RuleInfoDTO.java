package com.hunk.route.interfaces.facade.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author hunk
 * @date 2022/3/9
 *     <p>
 */
public class RuleInfoDTO implements Serializable {

    private final Long ruleId;

    private final String tradeType;

    private final String accountType;

    private final List<BankInfoDTO> bankInfos;

    private final String money;

    public RuleInfoDTO(
            Long ruleId,
            String tradeType,
            String accountType,
            List<BankInfoDTO> bankInfos,
            String money) {
        this.ruleId = ruleId;
        this.tradeType = tradeType;
        this.accountType = accountType;
        this.bankInfos = bankInfos;
        this.money = money;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getAccountType() {
        return accountType;
    }

    public List<BankInfoDTO> getBankInfos() {
        return bankInfos;
    }

    public String getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ruleId", ruleId)
                .append("tradeType", tradeType)
                .append("accountType", accountType)
                .append("bankInfos", bankInfos)
                .append("money", money)
                .toString();
    }
}
