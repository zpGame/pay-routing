package com.hunk.route.application.impl;

import com.hunk.route.application.RouteService;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.domain.event.RouteChannelEvent;
import com.hunk.route.domain.model.CreateInfo;
import com.hunk.route.domain.model.channel.PaymentChannel;
import com.hunk.route.domain.model.route.*;
import com.hunk.route.domain.model.rule.RouteRule;
import com.hunk.route.domain.model.rule.RouteRuleRepository;
import com.hunk.route.domain.model.rule.RuleNotFoundException;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public class RouteServiceImpl implements RouteService {

    private final RouteRuleRepository ruleRepository;
    private final RouteRepository routeRepository;
    private final CustomEventBus customEventBus;

    public RouteServiceImpl(
            RouteRuleRepository ruleRepository,
            RouteRepository routeRepository,
            CustomEventBus customEventBus) {
        this.ruleRepository = ruleRepository;
        this.routeRepository = routeRepository;
        this.customEventBus = customEventBus;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RouteChannel createRoute(
            PaymentChannel paymentChannel,
            String ruleId,
            EffectiveTime effectiveTime,
            String createUser) {
        RouteRule routeRule = ruleRepository.findRouteRuleByRuleId(ruleId);
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        ResultWithDomainEvents<RouteChannel, RouteChannelEvent> domainEvents =
                RouteChannel.createRoute(paymentChannel, routeRule, effectiveTime, createInfo);
        RouteChannel routeChannel = domainEvents.result;
        routeRepository.save(routeChannel);
        customEventBus.publish(domainEvents.event);
        return routeChannel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RouteChannel reviseInfo(
            long routeId,
            PaymentChannel paymentChannel,
            long ruleId,
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
