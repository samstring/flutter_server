package com.server.sf.server_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = "com.server.sf.server_user.user.model")
public class ServerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerUserApplication.class, args);
    }

}
