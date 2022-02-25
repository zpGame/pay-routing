package com.hunk.route.config;

import com.hunk.route.application.impl.RouteRuleServiceImpl;
import com.hunk.route.application.impl.RouteServiceImpl;
import com.hunk.route.domain.BankInfoRepository;
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
    public RouteRuleServiceImpl routeRuleService(BankInfoRepository bankInfoRepository) {
        return new RouteRuleServiceImpl(bankInfoRepository);
    }

    @Bean
    public RouteServiceImpl routeService(
            RouteRuleRepository ruleRepository, RouteRepository routeRepository) {
        return new RouteServiceImpl(ruleRepository, routeRepository);
    }
}
