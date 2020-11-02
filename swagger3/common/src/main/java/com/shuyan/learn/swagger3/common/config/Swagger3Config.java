package com.shuyan.learn.swagger3.common.config;

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
 * @author shuyan
 */
@EnableOpenApi
@Configuration
public class Swagger3Config {
    /**
     * 这个配置不配也可以使用
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        // 3.0 需要使用 DocumentationType.OAS_30
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("如有疑问，请联系开发工程师老刘。")
                .contact(new Contact("刘宏缔", "https://www.cnblogs.com/architectforest/", "371125307@qq.com"))
                .version("1.0")
                .build();
    }

    /*
     * todo:
     * 2. 统一鉴权
     * 3. 统一管理
     * 4. 集成校验注解
     */
}