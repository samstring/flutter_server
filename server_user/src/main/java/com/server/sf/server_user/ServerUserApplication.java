package com.server.sf.server_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableEurekaClient
//@EntityScan(basePackages = "com.server.sf.server_user.user.model")
public class ServerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerUserApplication.class, args);
    }

}
