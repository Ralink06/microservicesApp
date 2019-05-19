package com.ralink.user_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
@Slf4j
public class UserApplication {

    public static void main(final String[] args) {
        log.debug("Starting as a standalone gateway application");

        final SpringApplication app = new SpringApplication(UserApplication.class);

        app.run(args);
    }
}
