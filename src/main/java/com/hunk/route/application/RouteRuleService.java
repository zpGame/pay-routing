package com.hunk.route.application;

import com.hunk.route.domain.model.em.AccountType;
import com.hunk.route.domain.Money;
import com.hunk.route.domain.RouteRule;
import com.hunk.route.domain.model.em.TradeType;

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
     * @param bankIds 卡集
     * @param money 限额
     * @param createUser 创建人
     * @return RouteRule
     */
    RouteRule createRouteRule(
            TradeType tradeType,
            AccountType accountType,
            List<String> bankIds,
            Money money,
            String createUser);

    /**
     * 修改路由规则
     *
     * @param id 规则ID
     * @param tradeType 交易类型
     * @param accountType 账户类型
     * @param bankInfoIds 银行ID集
     * @param money 限额
     * @param modifyUser 修改人
     * @return RouteRule
     */
    RouteRule reviseInfo(
            Long id,
            TradeType tradeType,
            AccountType accountType,
            List<Long> bankInfoIds,
            Money money,
            String modifyUser);
}
