package com.hunk.route.domain.event;

import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 * @author hunk
 * @date 2022/4/2
 *     <p>
 */
@Getter
public class MerchantRouteEvent extends CustomEvent {

    private final String merchantId;

    private final String merchantNo;

    private final String merchantName;

    private final Set<String> channelIds;

    public MerchantRouteEvent(
            String merchantId, String merchantNo, String merchantName, Set<String> channelIds) {
        this.merchantId = merchantId;
        this.merchantNo = merchantNo;
        this.merchantName = merchantName;
        this.channelIds = channelIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("merchantId", merchantId)
                .append("merchantNo", merchantNo)
                .append("merchantName", merchantName)
                .append("channelIds", channelIds)
                .toString();
    }
}
