package com.hunk.route.interfaces.web;

import lombok.Data;

import java.util.List;

/**
 * @author hunk
 * @date 2022/3/9
 *     <p>
 */
@Data
public class RuleReviseCommand {

    private Long oriRuleId;

    private String alterTradeType;

    private String alterAccountType;

    private List<Long> alterBankInfoIds;

    private String alterMoney;

    private String modifyUser;
}
