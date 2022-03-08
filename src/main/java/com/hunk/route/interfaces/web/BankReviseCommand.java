package com.hunk.route.interfaces.web;

import lombok.Data;

/**
 * @author hunk
 * @date 2022/3/4
 * <p>
 */
@Data
public final class BankReviseCommand {

    private Long oriId;

    private String alterBankName;

    private String alterBankShortName;

    private String alterCardType;

    private String modifyUser;
}
