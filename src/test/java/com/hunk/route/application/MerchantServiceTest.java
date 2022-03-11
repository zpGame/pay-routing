package com.hunk.route.application;

import com.hunk.route.ApplicationTests;
import com.hunk.route.domain.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hunk
 * @date 2022/2/28
 *     <p>
 */
public class MerchantServiceTest extends ApplicationTests {

    @Autowired private MerchantService merchantService;

    @Test
    public void createMerchant() {
        String merchantNo = "4321541535234";
        String merchantName = "测试";
        List<Long> routeIds = new ArrayList<>();
        routeIds.add(1L);
        String createUser = "system";
        MerchantRoute merchant =
                merchantService.createMerchant(merchantNo, merchantName, routeIds, createUser);
        System.out.println(merchant);
    }

    @Test
    public void findById() {
        MerchantRoute merchantRoute = merchantService.findByMerchantNo("");
        RouteRule routeRule =
                merchantRoute.obtainRoutes().stream()
                        .map(RouteChannel::getRouteRule)
                        .filter(rule -> rule.validTradeType(TradeType.payment))
                        .filter(rule -> rule.validAccountType(AccountType.all))
                        .filter(rule -> rule.validMoney(new Money(9)))
                        .filter(
                                rule ->
                                        rule.getBankInfos().stream()
                                                        .filter(
                                                                bank ->
                                                                        bank.validBankShortName(
                                                                                "ICBC"))
                                                        .filter(
                                                                bank ->
                                                                        bank.validCardType(
                                                                                CardType.DebitCard))
                                                        .findFirst()
                                                        .orElse(null)
                                                != null)
                        .findFirst()
                        .orElse(null);
        System.out.println(routeRule);
    }

    @Test
    public void reviseInfo() {}
}
