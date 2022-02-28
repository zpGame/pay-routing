package com.hunk.route.application.impl;

import com.hunk.route.application.RouteService;
import com.hunk.route.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    @Transactional(rollbackFor = Exception.class)
    public RouteChannel createRoute(
            PaymentChannel paymentChannel,
            long ruleId,
            int priority,
            EffectiveTime effectiveTime,
            String createUser) {
        RouteRule routeRule =
                ruleRepository
                        .findById(ruleId)
                        .orElseThrow(() -> new RuleNotFoundException(ruleId));
        CreateInfo createInfo = CreateInfo.createInfo(createUser);

        RouteChannel routeChannel =
                RouteChannel.createRoute(
                        paymentChannel, routeRule, priority, effectiveTime, createInfo);
        return routeRepository.save(routeChannel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<RouteChannel> findById(long routeId) {
        return routeRepository.findById(routeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RouteChannel reviseInfo(
            long routeId,
            PaymentChannel paymentChannel,
            long ruleId,
            int priority,
            EffectiveTime effectiveTime,
            String modifyUser) {
        RouteChannel routeChannel =
                routeRepository
                        .findById(routeId)
                        .orElseThrow(() -> new RouteNotFoundException(routeId));
        RouteRule routeRule =
                ruleRepository
                        .findById(ruleId)
                        .orElseThrow(() -> new RuleNotFoundException(ruleId));
        CreateInfo createInfo = routeChannel.getCreateInfo().reviseInfo(modifyUser);
        RouteChannel newRouteChannel =
                routeChannel
                        .changePaymentChannel(paymentChannel)
                        .changeRouteRule(routeRule)
                        .changePriority(priority)
                        .changeCreateInfo(createInfo);
        return routeRepository.save(newRouteChannel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RouteChannel changeUpHold(long routeId, int isUpHold, String modifyUser) {
        RouteChannel routeChannel =
                routeRepository
                        .findById(routeId)
                        .orElseThrow(() -> new RouteNotFoundException(routeId));
        CreateInfo createInfo = routeChannel.getCreateInfo().reviseInfo(modifyUser);
        RouteChannel changeCreateInfo =
                RouteConstants.UPHOLD_OFF == isUpHold
                        ? routeChannel.changeCreateInfo(createInfo).changeUpHoldOff()
                        : routeChannel.changeCreateInfo(createInfo).changeUpHoldOn();
        return routeRepository.save(changeCreateInfo);
    }
}
