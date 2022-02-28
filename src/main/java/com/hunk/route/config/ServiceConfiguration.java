package com.hunk.route.config;

import com.hunk.route.application.impl.BankInfoServiceImpl;
import com.hunk.route.application.impl.MerchantServiceImpl;
import com.hunk.route.application.impl.RouteRuleServiceImpl;
import com.hunk.route.application.impl.RouteServiceImpl;
import com.hunk.route.domain.BankInfoRepository;
import com.hunk.route.domain.MerchantRepository;
import com.hunk.route.domain.RouteRepository;
import com.hunk.route.domain.RouteRuleRepository;
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
    public BankInfoServiceImpl bankInfoService(BankInfoRepository bankInfoRepository) {
        return new BankInfoServiceImpl(bankInfoRepository);
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
}
