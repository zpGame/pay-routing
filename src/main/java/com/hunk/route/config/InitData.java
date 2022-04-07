package com.hunk.route.config;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.application.MerchantService;
import com.hunk.route.application.RouteRuleService;
import com.hunk.route.application.RouteService;
import com.hunk.route.domain.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.thymeleaf.util.SetUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/3/1
 *     <p>测试需要
 */
public class InitData implements ApplicationRunner {

    private static final String CREATE_USER = "system";

    private final RouteService routeService;
    private final BankInfoService bankInfoService;
    private final MerchantService merchantService;
    private final RouteRuleService routeRuleService;

    public InitData(
            RouteService routeService,
            BankInfoService bankInfoService,
            MerchantService merchantService,
            RouteRuleService routeRuleService) {
        this.routeService = routeService;
        this.bankInfoService = bankInfoService;
        this.merchantService = merchantService;
        this.routeRuleService = routeRuleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BankInfo> bankInfos = initBankInfo();
        RouteRule routeRule = initRouteRule(bankInfos);
        RouteChannel routeChannel = initRouteChannel(routeRule);
        initMerchantRoute(routeChannel);
    }

    private void initMerchantRoute(RouteChannel routeChannel) {
        String merchantNo = "88888888";
        String merchantName = "山外山";
        Set<String> routeChannels = SetUtils.singletonSet(routeChannel.getChannelId());
        merchantService.createMerchant(merchantNo, merchantName, routeChannels, CREATE_USER);
    }

    private RouteChannel initRouteChannel(RouteRule routeRule) {
        PaymentChannel paymentChannel = new PaymentChannel(ChannelE.UNION, ServiceE.WITHHOLD);
        EffectiveTime effectiveTime =
                new EffectiveTime(
                        LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        return routeService.createRoute(
                paymentChannel, routeRule.getRuleId(), effectiveTime, CREATE_USER);
    }

    private RouteRule initRouteRule(List<BankInfo> bankInfos) {
        TradeType tradeType = TradeType.payment;
        AccountType accountType = AccountType.personal;
        Money money = new Money(12);
        List<String> bankIds =
                bankInfos.stream().map(BankInfo::getBankId).limit(3).collect(Collectors.toList());
        return routeRuleService.createRouteRule(
                tradeType, accountType, bankIds, money, CREATE_USER);
    }

    private List<BankInfo> initBankInfo() {
        List<BankInfo> bankInfos = new ArrayList<>();
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("中国工商银行", "ICBC"), CardType.DebitCard, CREATE_USER));
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("中国建设银行", "CBC"), CardType.DebitCard, CREATE_USER));
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("中国银行", "BC"), CardType.DebitCard, CREATE_USER));
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("中国农业银行", "ABC"), CardType.DebitCard, CREATE_USER));
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("民生银行", "CMSB"), CardType.DebitCard, CREATE_USER));
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("招商银行", "CMBC"), CardType.DebitCard, CREATE_USER));
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("兴业银行", "CIB"), CardType.DebitCard, CREATE_USER));
        bankInfos.add(
                bankInfoService.createBankInfo(
                        new BankName("国家开发银行", "CDB"), CardType.DebitCard, CREATE_USER));
        return bankInfos;
    }
}
