package com.hunk.route.application.impl;

import com.hunk.route.application.RouteService;
import com.hunk.route.domain.*;

import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public class RouteServiceImpl implements RouteService {

    private final RouteRuleRepository ruleRepository;

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRuleRepository ruleRepository, RouteRepository routeRepository) {
        this.ruleRepository = ruleRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public RouteChannel createRoute(
            PaymentChannel paymentChannel,
            long ruleId,
            int priority,
            LocalDateTime beginDate,
            LocalDateTime endDate) {
        RouteRule routeRule =
                ruleRepository
                        .findById(ruleId)
                        .orElseThrow(() -> new RuleNotFoundException(ruleId));
        RouteChannel routeChannel = RouteChannel.createRoute(paymentChannel, routeRule, priority, beginDate, endDate);
        routeRepository.save(routeChannel);
        return routeChannel;
    }
}
