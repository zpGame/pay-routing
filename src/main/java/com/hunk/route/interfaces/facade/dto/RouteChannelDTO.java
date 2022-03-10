package com.hunk.route.interfaces.facade.dto;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/3/10
 *     <p>
 */
@Getter
public class RouteChannelDTO implements Serializable {
    private final Long id;
    private final String routeInterface;
    private final String routeName;
    private final RuleInfoDTO ruleInfo;
    private final LocalDateTime beginDate;
    private final LocalDateTime endDate;
    private final Integer priority;
    private final Integer isUpHold;

    public RouteChannelDTO(
            Long id,
            String routeInterface,
            String routeName,
            RuleInfoDTO ruleInfo,
            LocalDateTime beginDate,
            LocalDateTime endDate,
            Integer priority,
            Integer isUpHold) {
        this.id = id;
        this.routeInterface = routeInterface;
        this.routeName = routeName;
        this.ruleInfo = ruleInfo;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.priority = priority;
        this.isUpHold = isUpHold;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("routeInterface", routeInterface)
                .append("routeName", routeName)
                .append("ruleInfo", ruleInfo)
                .append("beginDate", beginDate)
                .append("endDate", endDate)
                .append("priority", priority)
                .append("isUpHold", isUpHold)
                .toString();
    }
}
