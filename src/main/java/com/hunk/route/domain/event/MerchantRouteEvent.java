package com.hunk.route.domain.event;

import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 * @author hunk
 * @date 2022/4/2
 * <p>
 */
@Getter
public class MerchantRouteEvent extends CustomEvent {

    private final Long id;

    private final String merchantNo;

    private final String merchantName;

    private final Set<Long> routeChannelIds;

    public MerchantRouteEvent(Long id, String merchantNo, String merchantName, Set<Long> routeChannelIds) {
        this.id = id;
        this.merchantNo = merchantNo;
        this.merchantName = merchantName;
        this.routeChannelIds = routeChannelIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("merchantNo", merchantNo)
                .append("merchantName", merchantName)
                .append("routeChannelIds", routeChannelIds)
                .toString();
    }
}
