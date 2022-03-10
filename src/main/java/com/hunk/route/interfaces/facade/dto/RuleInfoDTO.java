package com.hunk.route.interfaces.facade.dto;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author hunk
 * @date 2022/3/9
 *     <p>
 */
@Getter
public class RuleInfoDTO implements Serializable {

    private final Long id;

    private final String tradeType;

    private final String accountType;

    private final List<BankInfoDTO> bankInfos;

    private final String money;

    /**
     * 前端菜鸡 先这么回显把
     */
    private String bankShortNames;
    private String bankIds;

    public String getBankShortNames() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bankInfos.size(); i++) {
            if (i == bankInfos.size() - 1){
                builder.append(bankInfos.get(i).getBankShortName());
            } else {
                builder.append(bankInfos.get(i).getBankShortName()).append(",");
            }
        }
        return builder.toString();
    }

    public String getBankIds() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bankInfos.size(); i++) {
            if (i == bankInfos.size() - 1){
                builder.append(bankInfos.get(i).getId());
            } else {
                builder.append(bankInfos.get(i).getId()).append(",");
            }
        }
        return builder.toString();
    }

    public RuleInfoDTO(
            Long id,
            String tradeType,
            String accountType,
            List<BankInfoDTO> bankInfos,
            String money) {
        this.id = id;
        this.tradeType = tradeType;
        this.accountType = accountType;
        this.bankInfos = bankInfos;
        this.money = money;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("tradeType", tradeType)
                .append("accountType", accountType)
                .append("bankInfos", bankInfos)
                .append("money", money)
                .toString();
    }
}
