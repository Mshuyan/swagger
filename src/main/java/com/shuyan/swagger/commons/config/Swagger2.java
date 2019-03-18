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
/*        HashSet<String> protocols = new HashSet<>();
        protocols.add("http");
        protocols.add("https");*/
        return new Docket(DocumentationType.SWAGGER_2)
               // .protocols(protocols)
                .apiInfo(apiInfo())
                //.globalResponseMessage(RequestMethod.POST,responseMessages())
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
        list.add(securityScheme());
        return list;
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint("https://github.com/login/oauth" + "/access_token", "access_token"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint("https://github.com/login/oauth" + "/authorize", "client-id", "client-secret"))
                .build();

        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
        return oauth;
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("foo", "Access foo API") };
        return scopes;
    }

    private List<ResponseMessage> responseMessages(){
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Map<String, Header> headers = new HashMap<>(4);
        headers.put("token",new Header("token","jwt令牌",new ModelRef("string")));
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("ok").headersWithDescription(headers).build());
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("请求参数错误").responseModel(new ModelRef("MessageResponse")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("未授权/授权失败").responseModel(new ModelRef("MessageResponse")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("路由不存在").responseModel(new ModelRef("MessageResponse")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(405).message("不支持的请求方法").responseModel(new ModelRef("MessageResponse")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("MessageResponse")).build());
        return responseMessageList;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
