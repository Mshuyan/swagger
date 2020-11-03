package com.shuyan.learn.swagger3.server2.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuth2SchemeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shuyan
 */
@Configuration
public class Swagger3Config {
    /**
     * 这个配置不配也可以使用
     * @return Docket
     */
    @Bean
    public Docket createRestApi(@Value("${spring.application.name}")String applicationName) {
        // 3.0 需要使用 DocumentationType.OAS_30
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                // 指定为本服务得服务名
                .pathMapping("/" + applicationName)
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Mobile Service API")
                .description("移动端 接口文档说明")
                .contact(new Contact("", "", ""))
                .version("1.0")
                .build();
    }

    private SecurityScheme securityScheme() {
        return new OAuth2SchemeBuilder("password")
                .name("账号密码登陆")
                .tokenUrl("/uaa/oauth/token")
                .scopes(Arrays.asList(scopes()))
                .build();
    }


    @SuppressWarnings("all")
    private SecurityContext securityContext() {
        List<String> ignoreUrls = Arrays.asList(
                "/weChat/login",
                "/weChat/phone/num",
                "/weChat/register",
                "/message/notice/page",
                "/content/content/list",
                "/content/item/page",
                "/feedback",
                "/order/icbc/callback"
        );
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(path -> ignoreUrls.stream().noneMatch(pattern -> new AntPathMatcher().match(pattern,path)))
                .build();
    }
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("all", "All scope is trusted!")
        };
    }
}
