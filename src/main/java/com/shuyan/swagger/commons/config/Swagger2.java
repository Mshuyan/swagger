package com.shuyan.swagger.commons.config;

import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

/**
 * @author will
 */
@Configuration
@EnableSwagger2
// @ConditionalOnProperty(prefix = "spring",value = {"profiles.active"},havingValue = "dev")
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())

                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.shuyan.swagger"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("测试swagger")
                //创建人、个人门户网站、邮箱
                .contact(new Contact("Will", "https://www.github.com/Mshuyan", "434224591@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("测试swagger使用")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> list = new ArrayList<>();
        /*
         * name：页面显示名称
         * keyname：自动携带的参数名称
         * passAs：自动携带的参数类型
         */
        list.add(new ApiKey("Authorization", "X-Auth-Token", "header"));
        return list;
    }
}
