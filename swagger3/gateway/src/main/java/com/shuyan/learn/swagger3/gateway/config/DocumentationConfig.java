package com.shuyan.learn.swagger3.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.RouteMatcher;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shuyan
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    public static final String API_URI = "/v3/api-docs";
    @Resource
    private RouteLocator routeLocator;
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        routeLocator.getRoutes().filter(route -> !applicationName.equals(route.getUri().getHost())).subscribe(route -> resources.add(swaggerResource(route.getUri().getHost())));
        return resources;
    }

    private SwaggerResource swaggerResource(String name) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation("/" + name + API_URI);
        swaggerResource.setSwaggerVersion("3.0");
        return swaggerResource;
    }
}
