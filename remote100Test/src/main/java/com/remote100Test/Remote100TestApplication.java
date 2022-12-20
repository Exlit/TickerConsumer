package com.remote100Test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Remote100TestApplication {
    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(Remote100TestApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        app.run(args);
    }
}
