package com.aikax28.springframework.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }
}
