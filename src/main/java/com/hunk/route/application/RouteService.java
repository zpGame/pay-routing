package com.hunk.route.application;

import com.hunk.route.domain.EffectiveTime;
import com.hunk.route.domain.PaymentChannel;
import com.hunk.route.domain.RouteChannel;

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
     * @param effectiveTime 有效时间
     * @param createUser 创建人
     * @return RouteChannel
     */
    RouteChannel createRoute(
            PaymentChannel paymentChannel,
            String ruleId,
            EffectiveTime effectiveTime,
            String createUser);

    /**
     * 修改路由信息
     *
     * @param routeId 路由ID
     * @param paymentChannel 路由渠道
     * @param ruleId 规则ID
     * @param effectiveTime 有效时间
     * @param modifyUser 修改人
     * @return RouteChannel
     */
    RouteChannel reviseInfo(
            long routeId,
            PaymentChannel paymentChannel,
            long ruleId,
            EffectiveTime effectiveTime,
            String modifyUser);

    /**
     * 是否维护
     *
     * @param routeId 路由ID
     * @param isUpHold 维护标识
     * @param modifyUser 修改人
     * @return RouteChannel
     */
    RouteChannel changeUpHold(long routeId, int isUpHold, String modifyUser);
}
