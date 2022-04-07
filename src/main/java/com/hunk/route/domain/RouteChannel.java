package com.hunk.route.domain;

import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.domain.event.RouteChannelEvent;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>路由
 */
@Entity
@Getter
@Table(name = "route_channel")
@org.hibernate.annotations.Table(appliesTo = "route_channel", comment = "路由表")
public class RouteChannel extends BaseEntity {

    public static ResultWithDomainEvents<RouteChannel, RouteChannelEvent> createRoute(
            PaymentChannel paymentChannel,
            RouteRule routeRule,
            EffectiveTime effectiveTime,
            CreateInfo createInfo) {
        RouteChannel routeChannel =
                new RouteChannel(paymentChannel, routeRule, effectiveTime, createInfo);
        RouteChannelEvent routeChannelEvent =
                new RouteChannelEvent(
                        routeChannel.getChannelId(),
                        paymentChannel,
                        effectiveTime,
                        routeChannel.getIsUpHold(),
                        routeRule.getRuleId());
        return new ResultWithDomainEvents<>(routeChannel, routeChannelEvent);
    }

    @Column(name = "channel_id", length = 32)
    private String channelId;

    @Embedded private PaymentChannel paymentChannel;

    /** 路由规则 */
    @OneToOne
    @JoinColumn(
            name = "associate_rule_id",
            referencedColumnName = "rule_id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private RouteRule routeRule = new RouteRule();
    /** 有效时间 */
    @Embedded private EffectiveTime effectiveTime;

    /** 是否维护 */
    private int isUpHold;

    public RouteChannel() {}

    public RouteChannel(
            PaymentChannel paymentChannel,
            RouteRule routeRule,
            EffectiveTime effectiveTime,
            CreateInfo createInfo) {
        this.channelId = MajorKey.getId();
        this.paymentChannel = paymentChannel;
        this.routeRule = routeRule;
        this.effectiveTime = effectiveTime;
        this.createInfo = createInfo;
        this.isUpHold = RouteConstants.UPHOLD_OFF;
    }

    public RouteChannel changePaymentChannel(PaymentChannel channel) {
        this.paymentChannel = channel;
        return this;
    }

    public RouteChannel changeRouteRule(RouteRule routeRule) {
        this.routeRule = routeRule;
        return this;
    }

    public RouteChannel changeCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
        return this;
    }

    public boolean isUpHold() {
        return this.isUpHold == RouteConstants.UPHOLD_OFF;
    }

    /**
     * 修改为维护状态
     *
     * @return RouteChannel
     */
    public RouteChannel changeUpHoldOn() {
        this.isUpHold = RouteConstants.UPHOLD_ON;
        return this;
    }

    /**
     * 修改为维护结束
     *
     * @return RouteChannel
     */
    public RouteChannel changeUpHoldOff() {
        this.isUpHold = RouteConstants.UPHOLD_OFF;
        return this;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        RouteChannel routeChannel = (RouteChannel) obj;

        return new EqualsBuilder().append(id, routeChannel.id).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("channelId", channelId)
                .append("paymentChannel", paymentChannel)
                .append("routeRule", routeRule)
                .append("effectiveTime", effectiveTime)
                .append("isUpHold", isUpHold)
                .append("createInfo", createInfo)
                .toString();
    }
}
