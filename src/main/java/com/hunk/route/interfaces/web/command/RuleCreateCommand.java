package com.hunk.route.interfaces.web.command;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hunk
 * @date 2022/3/9
 *     <p>
 */
@Data
public class RuleCreateCommand {
    private String tradeType;
    private String accountType;
    private List<Long> bankInfoIds = new ArrayList<>();
    private String money;
    private String createUser;
}