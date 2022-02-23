package com.hunk.route.domain;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/21
 *     <p>
 */
@Slf4j
public class RouteService {

    private RouteRuleRepository ruleRepository;

    private MerchantRepository merchantRepository;

    private RouteRepository routeRepository;

    public RouteService(
            RouteRuleRepository ruleRepository,
            MerchantRepository merchantRepository,
            RouteRepository routeRepository) {
        this.ruleRepository = ruleRepository;
        this.merchantRepository = merchantRepository;
        this.routeRepository = routeRepository;
    }

    public void createRoute(PaymentChannel paymentChannel,
                             RouteRule routeRule,
                             int priority,
                             LocalDateTime beginDate,
                             LocalDateTime endDate){


        Route route = Route.createRoute(paymentChannel, routeRule, priority, beginDate, endDate);
    }
}
