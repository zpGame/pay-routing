package com.hunk.route.config;

import com.hunk.route.application.*;
import com.hunk.route.application.impl.*;
import com.hunk.route.domain.*;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hunk
 * @date 2022/2/21
 *     <p>
 */
@Configuration
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
            RouteRuleRepository ruleRepository, RouteRepository routeRepository) {
        return new RouteServiceImpl(ruleRepository, routeRepository);
    }

    @Bean
    public MerchantService merchantService(
            MerchantRepository merchantRepository, RouteRepository routeRepository) {
        return new MerchantServiceImpl(merchantRepository, routeRepository);
    }

    @Bean
    public InitData initData(
            RouteRepository routeRepository,
            BankInfoService bankInfoService,
            MerchantRepository merchantRepository,
            RouteRuleService routeRuleService) {
        return new InitData(routeRepository, bankInfoService, merchantRepository, routeRuleService);
    }
}
