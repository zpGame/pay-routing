package com.hunk.route.application.impl;

import com.hunk.route.application.RouteRuleService;
import com.hunk.route.domain.*;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.domain.event.RouteRuleEvent;
import com.hunk.route.domain.model.bank.BankInfo;
import com.hunk.route.domain.model.bank.BankInfoRepository;
import com.hunk.route.domain.model.em.AccountType;
import com.hunk.route.domain.model.em.TradeType;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public class RouteRuleServiceImpl implements RouteRuleService {

    private final CustomEventBus customEventBus;
    private final BankInfoRepository bankInfoRepository;
    private final RouteRuleRepository routeRuleRepository;

    public RouteRuleServiceImpl(
            CustomEventBus customEventBus,
            BankInfoRepository bankInfoRepository,
            RouteRuleRepository routeRuleRepository) {
        this.customEventBus = customEventBus;
        this.bankInfoRepository = bankInfoRepository;
        this.routeRuleRepository = routeRuleRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RouteRule createRouteRule(
            TradeType tradeType,
            AccountType accountType,
            List<String> bankIds,
            Money money,
            String createUser) {
        Set<BankInfo> bankInfos =
                bankIds.stream()
                        .map(bankInfoRepository::findBankInfoByBankId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        ResultWithDomainEvents<RouteRule, RouteRuleEvent> domainEvents =
                RouteRule.createRouteRule(tradeType, accountType, bankInfos, money, createInfo);
        RouteRule routeRule = domainEvents.result;
        routeRuleRepository.save(routeRule);
        customEventBus.publish(domainEvents.event);
        return routeRule;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RouteRule reviseInfo(
            Long id,
            TradeType tradeType,
            AccountType accountType,
            List<Long> bankInfoIds,
            Money money,
            String modifyUser) {
        RouteRule routeRule =
                routeRuleRepository.findById(id).orElseThrow(() -> new RuleNotFoundException(id));
        Set<BankInfo> bankInfos =
                bankInfoIds.stream()
                        .map(bankInfoRepository::findById)
                        .map(bankInfo -> bankInfo.orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
        CreateInfo createInfo = routeRule.getCreateInfo().reviseInfo(modifyUser);
        RouteRule changeRouteRule =
                routeRule
                        .changeTradeType(tradeType)
                        .changeAccountType(accountType)
                        .changeBankInfos(bankInfos)
                        .changeMoney(money)
                        .changeCreateInfo(createInfo);
        return routeRuleRepository.save(changeRouteRule);
    }
}
