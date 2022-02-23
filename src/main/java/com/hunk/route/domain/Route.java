package com.hunk.route.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>路由
 */
@Entity
@Table(name = "route")
public class Route {

    @Id @GeneratedValue private Long id;

    private String routeId;

    @Embedded private PaymentChannel paymentChannel;

    /** 路由规则 */
    @OneToOne private RouteRule routeRule;
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
    public static Route createRoute(
            PaymentChannel paymentChannel,
            RouteRule routeRule,
            int priority,
            LocalDateTime beginDate,
            LocalDateTime endDate) {
        return new Route(paymentChannel, routeRule, priority, beginDate, endDate);
    }

    public Route() {}

    public Route(
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
     * @return Route
     */
    public Route changeUpHoldOn() {
        this.isUpHold = RouteConstants.UPHOLD_ON;
        return this;
    }

    /**
     * 修改为维护结束
     *
     * @return Route
     */
    public Route changeUpHoldOff() {
        this.isUpHold = RouteConstants.UPHOLD_OFF;
        return this;
    }
}
