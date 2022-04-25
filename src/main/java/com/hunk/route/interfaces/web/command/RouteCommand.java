package com.hunk.route.interfaces.web.command;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/3/11
 *     <p>
 */
@Data
public class RouteCommand {
    private Long id;
    private String channelE;
    private String serviceE;
    private String ruleId;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private Integer isUpHold;
    private String createUser;
    private String modifyUser;
}
