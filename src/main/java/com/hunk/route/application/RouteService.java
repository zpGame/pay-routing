package com.hunk.route.application;

import com.hunk.route.domain.PaymentChannel;
import com.hunk.route.domain.RouteChannel;

import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public interface RouteService {

    /**
     * 创建路由
     *
     * @param paymentChannel 路由渠道
     * @param ruleId 规则 id
     * @param priority 优先级
     * @param beginDate 有效时间
     * @param endDate 有效时间
     * @return RouteChannel
     */
    RouteChannel createRoute(
            PaymentChannel paymentChannel,
            long ruleId,
            int priority,
            LocalDateTime beginDate,
            LocalDateTime endDate);
}
