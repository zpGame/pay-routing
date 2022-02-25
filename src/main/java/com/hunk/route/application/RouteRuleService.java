package com.hunk.route.application;

import com.hunk.route.domain.AccountType;
import com.hunk.route.domain.Money;
import com.hunk.route.domain.RouteRule;
import com.hunk.route.domain.TradeType;

import java.util.List;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public interface RouteRuleService {

    /**
     * 创建路由规则
     *
     * @param tradeType 交易类型
     * @param accountType 账户类型
     * @param bankInfoIds 卡集
     * @param money 限额
     * @return RouteRule
     */
    RouteRule createRouteRule(
            TradeType tradeType, AccountType accountType, List<Long> bankInfoIds, Money money);
}
