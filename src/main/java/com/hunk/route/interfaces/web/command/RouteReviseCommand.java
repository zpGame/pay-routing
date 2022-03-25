package com.hunk.route.interfaces.web.command;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/3/11
 *     <p>
 */
@Data
public class RouteReviseCommand {
    private Long oriId;
    private String alterChannelE;
    private String alterServiceE;
    private Long alterRuleId;
    private LocalDateTime alterBeginDate;
    private LocalDateTime alterBndDate;
    private Integer alterIsUpHold;
    private String modifyUser;
}
