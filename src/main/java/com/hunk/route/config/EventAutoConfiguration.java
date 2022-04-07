package com.hunk.route.config;

import com.hunk.route.domain.event.BankInfoListen;
import com.hunk.route.domain.event.MerchantRouteListen;
import com.hunk.route.domain.event.RouteChannelListen;
import com.hunk.route.domain.event.RouteRuleListen;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import com.hunk.route.infrastructure.messaging.event.injvm.DefaultCustomEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hunk
 * @date 2022/3/18
 *     <p>eventBus
 */
@Configuration
public class EventAutoConfiguration {

    @Bean
    public CustomEventBus defaultCustomEventBus() {
        return new DefaultCustomEventBus();
    }

    @Bean
    public MerchantRouteListen merchantRouteListen() {
        return new MerchantRouteListen();
    }

    @Bean
    public BankInfoListen bankInfoListen() {
        return new BankInfoListen();
    }

    @Bean
    public RouteRuleListen routeRuleListen() {
        return new RouteRuleListen();
    }

    @Bean
    public RouteChannelListen routeChannelListen() {
        return new RouteChannelListen();
    }
}
