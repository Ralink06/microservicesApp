package com.ralink.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@Slf4j
public class GatewayApplication {

    public static void main(final String[] args) {
        log.debug("Starting as a standalone gateway application");

        final SpringApplication app = new SpringApplication(GatewayApplication.class);

        app.run(args);
    }

    @Bean
    public RestTemplate rest(final RestTemplateBuilder builder) {
        return builder.build();
    }
}

