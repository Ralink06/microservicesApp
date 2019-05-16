package com.ralink.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class GatewayApplication {

    public static void main(final String[] args) {
        log.debug("Starting as a standalone application");

        SpringApplication app = new SpringApplication(GatewayApplication.class);

        app.run(args);
    }
}
