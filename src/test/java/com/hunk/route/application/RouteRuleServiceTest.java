package com.hunk.route.application;

import com.hunk.route.ApplicationTests;
import com.hunk.route.domain.AccountType;
import com.hunk.route.domain.Money;
import com.hunk.route.domain.RouteRule;
import com.hunk.route.domain.TradeType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hunk
 * @date 2022/2/28
 *     <p>
 */
public class RouteRuleServiceTest extends ApplicationTests {

    @Autowired private RouteRuleService routeRuleService;

    @Test
    public void createRouteRule() {
        TradeType tradeType = TradeType.payment;
        AccountType accountType = AccountType.all;
        List<Long> bankInfoIds = new ArrayList<>();
        bankInfoIds.add(1L);
        bankInfoIds.add(2L);
        Money money = new Money(10);
        String createUser = "system";
        RouteRule routeRule =
                routeRuleService.createRouteRule(
                        tradeType, accountType, bankInfoIds, money, createUser);
        System.out.println(routeRule);
    }

    @Test
    public void findById() {
        System.out.println(routeRuleService.findById(1L));
    }

    @Test
    public void reviseInfo() {}
}
