package com.hunk.route.main;

import com.hunk.route.config.ServiceWithRepositoriesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>启动类
 */
@SpringBootApplication
@Import({ServiceWithRepositoriesConfiguration.class})
public class RouteMain {

    public static void main(String[] args) {
        SpringApplication.run(RouteMain.class, args);
    }
}
