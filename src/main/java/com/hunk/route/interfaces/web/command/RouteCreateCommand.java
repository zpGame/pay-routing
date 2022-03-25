package com.hunk.route.interfaces.web.command;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/3/11
 * <p>
 */
@Data
public class RouteCreateCommand {

    private String channelE;
    private String serviceE;
    private Long ruleId;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private Integer isUpHold;
    private String createUser;

}
