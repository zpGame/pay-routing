package com.hunk.route.config;

import com.hunk.route.domain.MerchantRepository;
import com.hunk.route.domain.RouteRepository;
import com.hunk.route.domain.RouteRuleRepository;
import com.hunk.route.domain.RouteService;
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
    public RouteService routeService(
            RouteRuleRepository ruleRepository,
            MerchantRepository merchantRepository,
            RouteRepository routeRepository) {
        return new RouteService(ruleRepository, merchantRepository, routeRepository);
    }
}
