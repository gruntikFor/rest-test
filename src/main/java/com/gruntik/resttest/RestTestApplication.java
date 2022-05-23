package com.gruntik.resttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootApplication
public class RestTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTestApplication.class, args);
    }

}
