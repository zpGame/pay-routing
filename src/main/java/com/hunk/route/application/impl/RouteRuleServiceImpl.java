package com.hunk.route.application.impl;

import com.hunk.route.application.RouteRuleService;
import com.hunk.route.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public class RouteRuleServiceImpl implements RouteRuleService {

    private final BankInfoRepository bankInfoRepository;
    private final RouteRuleRepository routeRuleRepository;

    public RouteRuleServiceImpl(
            BankInfoRepository bankInfoRepository, RouteRuleRepository routeRuleRepository) {
        this.bankInfoRepository = bankInfoRepository;
        this.routeRuleRepository = routeRuleRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RouteRule createRouteRule(
            TradeType tradeType,
            AccountType accountType,
            List<Long> bankInfoIds,
            Money money,
            String createUser) {
        Set<BankInfo> bankInfos =
                bankInfoIds.stream()
                        .map(bankInfoRepository::findById)
                        .map(bankInfo -> bankInfo.orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        RouteRule routeRule =
                RouteRule.createRouteRule(tradeType, accountType, bankInfos, money, createInfo);
        return routeRuleRepository.save(routeRule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<RouteRule> findById(Long id) {
        return routeRuleRepository.findById(id);
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
                routeRuleRepository
                        .findById(id)
                        .orElseThrow(() -> new RuleNotFoundException(id));
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
