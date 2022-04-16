package com.hunk.route;

import com.hunk.route.config.ServiceWithRepositoriesConfiguration;
import com.hunk.route.infrastructure.context.ApplicationUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>启动类
 */
@SpringBootApplication
@Import({ServiceWithRepositoriesConfiguration.class})
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ApplicationUtils.set(context);
    }
}
