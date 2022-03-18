package com.hunk.route.config;

import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import com.hunk.route.infrastructure.messaging.event.injvm.DefaultCustomEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hunk
 * @date 2022/3/18
 *     <p>
 */
@Configuration
public class EventAutoConfiguration {

    @Bean
    public CustomEventBus defaultCustomEventBus() {
        return new DefaultCustomEventBus();
    }
}
