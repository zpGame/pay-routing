package com.hunk.route.domain;

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
public class RouteChannel extends BaseEntity {

    @Embedded private PaymentChannel paymentChannel;

    /** 路由规则 */
    @OneToOne
    @JoinColumn(
            name = "associate_rule_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private RouteRule routeRule = new RouteRule();
    /** 优先级 */
    private int priority;
    /** 有效时间 */
    @Embedded private EffectiveTime effectiveTime;

    /** 是否维护 */
    private int isUpHold;

    /**
     * 创建路由
     *
     * @param routeRule 路由规则
     * @param priority 优先级java
     * @param effectiveTime 有效时间
     * @param createInfo 创建信息
     * @return route
     */
    public static RouteChannel createRoute(
            PaymentChannel paymentChannel,
            RouteRule routeRule,
            int priority,
            EffectiveTime effectiveTime,
            CreateInfo createInfo) {
        return new RouteChannel(paymentChannel, routeRule, priority, effectiveTime, createInfo);
    }

    public RouteChannel() {}

    public RouteChannel(
            PaymentChannel paymentChannel,
            RouteRule routeRule,
            int priority,
            EffectiveTime effectiveTime,
            CreateInfo createInfo) {
        this.paymentChannel = paymentChannel;
        this.routeRule = routeRule;
        this.priority = priority;
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

    public RouteChannel changePriority(int priority) {
        this.priority = priority;
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
                .append("paymentChannel", paymentChannel)
                .append("routeRule", routeRule)
                .append("priority", priority)
                .append("effectiveTime", effectiveTime)
                .append("isUpHold", isUpHold)
                .append("createInfo", createInfo)
                .toString();
    }
}
