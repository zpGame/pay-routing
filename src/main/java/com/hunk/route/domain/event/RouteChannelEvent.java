package com.hunk.route.domain.event;

import com.hunk.route.domain.model.route.EffectiveTime;
import com.hunk.route.domain.model.channel.PaymentChannel;
import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author hunk
 * @date 2022/4/6
 *     <p>
 */
@Getter
public class RouteChannelEvent extends CustomEvent {

    private final String channelId;
    private final PaymentChannel paymentChannel;
    private final EffectiveTime effectiveTime;
    private final int isUpHold;
    private final String ruleId;

    public RouteChannelEvent(
            String channelId,
            PaymentChannel paymentChannel,
            EffectiveTime effectiveTime,
            int isUpHold,
            String ruleId) {
        this.channelId = channelId;
        this.paymentChannel = paymentChannel;
        this.effectiveTime = effectiveTime;
        this.isUpHold = isUpHold;
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("channelId", channelId)
                .append("paymentChannel", paymentChannel)
                .append("effectiveTime", effectiveTime)
                .append("isUpHold", isUpHold)
                .append("ruleId", ruleId)
                .toString();
    }
}
