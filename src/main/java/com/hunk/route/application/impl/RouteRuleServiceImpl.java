package com.hunk.route.application.impl;

import com.hunk.route.application.RouteRuleService;
import com.hunk.route.domain.*;

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

    private final BankInfoRepository bankInfoRepository;

    public RouteRuleServiceImpl(BankInfoRepository bankInfoRepository) {
        this.bankInfoRepository = bankInfoRepository;
    }

    @Override
    public RouteRule createRouteRule(
            TradeType tradeType, AccountType accountType, List<Long> bankInfoIds, Money money) {
        Set<BankInfo> bankInfos =
                bankInfoIds.stream()
                        .map(bankInfoRepository::findById)
                        .map(bankInfo -> bankInfo.orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
        return RouteRule.createRouteRule(tradeType, accountType, bankInfos, money);
    }
}
