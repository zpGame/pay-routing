package com.hunk.route.interfaces.web.command;

import lombok.Data;

/**
 * @author hunk
 * @date 2022/3/4
 *     <p>
 */
@Data
public final class BankCommand {

    private Long id;

    private String bankName;

    private String bankShortName;

    private String cardType;

    private String createUser;

    private String modifyUser;
}
