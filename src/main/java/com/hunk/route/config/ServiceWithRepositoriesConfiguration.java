package com.hunk.route.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author hunk
 * @date 2022/2/21
 * <p>
 */
@Configuration
@EnableJpaRepositories( "com.hunk.route.domain" )
@EnableAutoConfiguration
@EntityScan("com.hunk.route.domain")
@Import({ServiceConfiguration.class})
public class ServiceWithRepositoriesConfiguration {
}
