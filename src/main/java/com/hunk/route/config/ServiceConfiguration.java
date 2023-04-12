package com.hunk.route.config;

import com.hunk.route.application.*;
import com.hunk.route.application.impl.*;
import com.hunk.route.domain.model.bank.BankInfoRepository;
import com.hunk.route.domain.model.channel.ChannelCostRepository;
import com.hunk.route.domain.model.merchant.MerchantRepository;
import com.hunk.route.domain.model.route.RouteRepository;
import com.hunk.route.domain.model.rule.RouteRuleRepository;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import org.springframework.context.annotation.Bean;

/**
 * @author hunk
 * @date 2022/2/21
 *     <p>
 */
public class ServiceConfiguration {

    @Bean
    public ChannelCostService channelCostService(ChannelCostRepository channelCostRepository) {
        return new ChannelCostServiceImpl(channelCostRepository);
    }

    @Bean
    public BankInfoService bankInfoService(
            BankInfoRepository bankInfoRepository, CustomEventBus customEventBus) {
        return new BankInfoServiceImpl(bankInfoRepository, customEventBus);
    }

    @Bean
    public RouteRuleService routeRuleService(
            CustomEventBus customEventBus,
            BankInfoRepository bankInfoRepository,
            RouteRuleRepository routeRuleRepository) {
        return new RouteRuleServiceImpl(customEventBus, bankInfoRepository, routeRuleRepository);
    }

    @Bean
    public RouteService routeService(
            CustomEventBus customEventBus,
            RouteRuleRepository ruleRepository,
            RouteRepository routeRepository) {
        return new RouteServiceImpl(ruleRepository, routeRepository, customEventBus);
    }

    @Bean
    public MerchantService merchantService(
            CustomEventBus customEventBus,
            MerchantRepository merchantRepository,
            RouteRepository routeRepository) {
        return new MerchantServiceImpl(merchantRepository, routeRepository, customEventBus);
    }

    @Bean
    public InitData initData(
            RouteService routeService,
            BankInfoService bankInfoService,
            MerchantService merchantService,
            RouteRuleService routeRuleService) {
        return new InitData(routeService, bankInfoService, merchantService, routeRuleService);
    }
}
