package com.hunk.route.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author hunk
 * @date 2022/3/12
 *     <p>Swagger UI 配置信息
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    /** 添加摘要信息(Docket) */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // API基础扫描路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API实时接口文档")
                .description("用于查看及API测试")
                .contact(contact())
                .version("1.0")
                .build();
    }

    private Contact contact() {
        return new Contact(
                "hunk", "https://github.com/zpGame/pay-routing.git", "zpGamble@live.com");
    }
}
