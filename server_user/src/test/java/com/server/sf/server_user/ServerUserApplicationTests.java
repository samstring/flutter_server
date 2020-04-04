package com.server.sf.server_user;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
@Log4j2
class ServerUserApplicationTests {

    @Test
    void contextLoads() {
        String password =new BCryptPasswordEncoder().encode("secret");
     System.out.println("-------"+password);
    }

}
