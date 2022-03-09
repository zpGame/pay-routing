package com.hunk.route.interfaces.web;

import lombok.Data;

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
    private List<Long> bankInfoIds;
    private String money;
    private String createUser;
}
