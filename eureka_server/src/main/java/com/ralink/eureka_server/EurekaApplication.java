package com.ralink.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(final String[] args) {
        final SpringApplication app = new SpringApplication(EurekaApplication.class);

        app.run(args);
    }
}
