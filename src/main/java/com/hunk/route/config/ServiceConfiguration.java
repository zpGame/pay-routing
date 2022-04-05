package com.hunk.route.config;

import com.hunk.route.application.impl.*;
import com.hunk.route.domain.*;
import com.hunk.route.infrastructure.messaging.event.injvm.DefaultCustomEventBus;
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
    public ChannelCostServiceImpl channelCostService(ChannelCostRepository channelCostRepository) {
        return new ChannelCostServiceImpl(channelCostRepository);
    }

    @Bean
    public BankInfoServiceImpl bankInfoService(
            BankInfoRepository bankInfoRepository, DefaultCustomEventBus defaultCustomEventBus) {
        return new BankInfoServiceImpl(bankInfoRepository, defaultCustomEventBus);
    }

    @Bean
    public RouteRuleServiceImpl routeRuleService(
            BankInfoRepository bankInfoRepository, RouteRuleRepository routeRuleRepository) {
        return new RouteRuleServiceImpl(bankInfoRepository, routeRuleRepository);
    }

    @Bean
    public RouteServiceImpl routeService(
            RouteRuleRepository ruleRepository, RouteRepository routeRepository) {
        return new RouteServiceImpl(ruleRepository, routeRepository);
    }

    @Bean
    public MerchantServiceImpl merchantService(
            MerchantRepository merchantRepository, RouteRepository routeRepository) {
        return new MerchantServiceImpl(merchantRepository, routeRepository);
    }

    @Bean
    public InitData initData(
            RouteRepository routeRepository,
            BankInfoRepository bankInfoRepository,
            MerchantRepository merchantRepository,
            RouteRuleRepository routeRuleRepository) {
        return new InitData(
                routeRepository, bankInfoRepository, merchantRepository, routeRuleRepository);
    }
}
