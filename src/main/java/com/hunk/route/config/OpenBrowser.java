package com.hunk.route.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author hunk
 * @date 2022/3/10
 *     <p>
 */
@Slf4j
@Component
public class OpenBrowser implements CommandLineRunner {

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String path;

    @Override
    public void run(String... args) throws Exception {
        printUrl();
    }

    @SuppressWarnings("all")
    private void printUrl() {
        try {
            log.info(
                    "HomePage : http://{}:{}{}",
                    InetAddress.getLocalHost().getHostAddress(),
                    port,
                    path);
            log.info(
                    "swagger  : http://{}:{}{}/swagger-ui/index.html",
                    InetAddress.getLocalHost().getHostAddress(),
                    port,
                    path);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings("all")
    private void openBrowser() throws Exception {
        try {
            // 指定自己项目的路径
            String command =
                    "cmd "
                            + "/c "
                            + "start "
                            + "http://"
                            + InetAddress.getLocalHost().getHostAddress()
                            + ":"
                            + port
                            + path;
            Runtime.getRuntime().exec(command);
        } catch (Exception ignored) {

        }
    }
}
