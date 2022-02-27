package com.hunk.route.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>路由
 */
@Entity
@Table(name = "route_channel")
public class RouteChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded private PaymentChannel paymentChannel;

    /** 路由规则 */
    @OneToOne
    @JoinColumn(
            name = "associate_rule_id",
            referencedColumnName = "rule_id",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private RouteRule routeRule = new RouteRule();
    /** 优先级 */
    private int priority;
    /** 有效时间 */
    private LocalDateTime beginDate;

    private LocalDateTime endDate;
    /** 是否维护 */
    private int isUpHold;

    /**
     * 创建路由
     *
     * @param routeRule 路由规则
     * @param priority 优先级java
     * @param beginDate 生效时间
     * @param endDate 过期时间
     * @return route
     */
    public static RouteChannel createRoute(
            PaymentChannel paymentChannel,
            RouteRule routeRule,
            int priority,
            LocalDateTime beginDate,
            LocalDateTime endDate) {
        return new RouteChannel(paymentChannel, routeRule, priority, beginDate, endDate);
    }

    public RouteChannel() {}

    public RouteChannel(
            PaymentChannel paymentChannel,
            RouteRule routeRule,
            int priority,
            LocalDateTime beginDate,
            LocalDateTime endDate) {
        this.paymentChannel = paymentChannel;
        this.routeRule = routeRule;
        this.priority = priority;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isUpHold = RouteConstants.UPHOLD_OFF;
    }

    public PaymentChannel getPaymentChannel() {
        return paymentChannel;
    }

    public RouteRule getRouteRule() {
        return routeRule;
    }

    public int getPriority() {
        return priority;
    }

    public boolean validTime() {
        return LocalDateTime.now().isAfter(beginDate) && LocalDateTime.now().isBefore(endDate);
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
                .append("beginDate", beginDate)
                .append("endDate", endDate)
                .append("isUpHold", isUpHold)
                .toString();
    }
}
