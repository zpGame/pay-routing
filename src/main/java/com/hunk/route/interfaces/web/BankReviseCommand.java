package com.hunk.route.interfaces.web;

import lombok.Data;

/**
 * @author hunk
 * @date 2022/3/4
 * <p>
 */
@Data
public final class BankReviseCommand {

    private Long id;

    private String bankName;

    private String bankShortName;

    private String cardType;

    private String modifyUser;
}
